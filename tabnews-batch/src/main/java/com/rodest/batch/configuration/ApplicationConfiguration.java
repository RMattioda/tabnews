package com.rodest.batch.configuration;

import com.rodest.configuration.TabnewsApiConfigData;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@AllArgsConstructor
public class ApplicationConfiguration {

	private final TabnewsApiConfigData tabnewsApiConfigData;

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder()
			.uriTemplateHandler(new DefaultUriBuilderFactory(tabnewsApiConfigData.getConnectionUrl()))
			.build();
	}
}
