import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.rodest.configuration.ElasticConfigData;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.client.erhlc.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.rodest.elasticindex.repository")
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    private final ElasticConfigData elasticConfigData;

    public ElasticSearchConfig(ElasticConfigData elasticConfigData) {
        this.elasticConfigData = elasticConfigData;
    }

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder()
			.connectedTo(elasticConfigData.getConnectionUrl())
			.withConnectTimeout(elasticConfigData.getConnectTimeoutMs())
			.withSocketTimeout(elasticConfigData.getSocketTimeoutMs())
			.withClientConfigurer(clientConfigurer -> {
				System.out.println("passou");
				return clientConfigurer;
			})
			.build();

	}

//	@Bean
//	public ElasticsearchClient elasticsearchClient(){
//
//		var httpClient = RestClient.builder(
//			HttpHost.create(elasticConfigData.getConnectionUrl())
//		).build();
//
//		var transport = new RestClientTransport(httpClient, new JacksonJsonpMapper());
//
//		return new ElasticsearchClient(transport);
//	}

}
