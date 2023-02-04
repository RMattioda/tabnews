# Tabnews

## Apresentação e motivação
Me chamo Rodrigo Mattioda, tenho 27 anos e moro no Rio Grande do Sul. Trabalho com programação a mais de 5 anos. Achei muito bacana essa ideia do Tabnews e o que me motivou a fazer esse projeto foi contribuir de alguma forma com a comunidade, é algo que eu sempre quis fazer e nunca achei a melhor forma de realizar isso. Outro motivador foi que recentemente o Spring Boot atualizou suas versões starters para 3.0 com o uso do Java 17 por padrão e Spring 6.0 e eu queria testar suas novas funcionalidades. Bom, não preciso dizer que os planetas se alinharam e eu comecei o desenvolvimento.

## Início
Tive algumas ideias de como seria a arquitetura inicial, pensei em talvez criar um sistema monolítico apenas para implementar a busca, mas dessa forma eu não conseguiria testar todos os recursos novos.

Pensando que no futuro eu pudesse escalar esse serviço e implementar mais funcionalidades eu resolvi optar pela utilização de uma arquitetura distribuída com micro serviços. segue um link sobre a decisão arquitetural: [Modelo Arquitetural](https://www.flickr.com/photos/63395520@N05/52668899785/in/dateposted-public/)

## Visão geral
Comecei utilizando um conceito de multi módulos do maven para construir o projeto. Dentro do módulo principal eu defini quais são as dependências que serão utilizadas, também quais serão os serviços presentes no domínio e algumas outras coisas como docker-compose e o repo de configuração dos serviços.

Em ordem de criação vou comentar um pouco sobre cada serviços e suas funcionalidades.

## Tabnews Config Server
É um servidor de configurações. Seu objetivo externalizar as configurações dos serviços e servir elas de forma dinâmica. Com o spring cloud config é muito simples criar esse tipo de funcionalidade, apenas algumas linhas em um arquivo e já está no ar.

## Tabnews Application Configuration
O application configuration é um módulo comum entre todos os serviços que necessitam de alguma configuração que esteja externalizada, ele tem como objetivo servir em forma de classes java todas as configurações que são distribuídas pelo config-server. Dessa forma consegue-se fazer o reuso de classes e removemos o fator redundância dos serviços.

## Tabnews Elastic
É um conjunto de módulos que tem como objetivo fornecer funcionalidades comuns a qualquer serviço que queira utilizar o elasticsearch. Aqui estão as conexões com o elasticsearch, as classes que representam os dados armazenados nele, interfaces para operações comuns como persistência e recuperação dos dados e etc.

## Tabnews Batch
Esse serviço batch é utilizado única e exclusivamente para capturar todos os registros retornados pela API publica do tabnews e atualizar os dados do Elasticsearch. Cada vez que esse batch rodar os dados são buscados na API pública do tabnews e em seguida adicionados/atualizados no elastic.

O spring batch é um módulo do spring que permite o processamento de grandes volumes de dados em lotes utilizando o conceito de jobs, que executa uma sequência de passos pré determinados.

Existem duas abordagens que podem ser utilizadas para o processamento de dados, utilizando Tasklets ou Chuncks (a diferença pode ser encontrada nesse link [https://www.baeldung.com/spring-batch-tasklet-chunk](https://www.baeldung.com/spring-batch-tasklet-chunk)) . Optei por utilizar Tasklets para criação do JOB por ser um modelo mais simples e que atende perfeitamente o objetivo.

Em seguida, utilizei as funcionalidades desenvolvidas no tabnews elastic para poder salvar os dados diretamente na API do elasticsearch.

## Tabnews Search Service
Finalmente chegamos a última parte desse quebra cabeça. O tabnews search service é a API disponibilizada para web. Utilizada para realizar as buscas que foram indexadas pelo elasticsearch, no momento possui apenas 3 buscas, sendo elas:

- Busca por id
- Busca por título da publicação
- Busca paginada de todos os registros

Claro, futuramente poderá ser possível realizar os mais diversos tipos de buscas com os mais diferentes filtros. Pode ficar para uma próxima release.

## Docker Compose

Não é um módulo da aplicação. Possui arquivos para que sejam rodados via docker compose onde constroem um cluster elastic e todos os serviços comentados acima.

~~~shell
docker-compose -f .\common.yml -f .\services.yml -f .\elastic_cluster.yml up
~~~
