package com.rodest.tabnewssearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.rodest")
@ComponentScan(value = "com.rodest")
public class TabnewsSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabnewsSearchServiceApplication.class);
	}
}
