package com.rodest.batch.configuration;

import com.rodest.batch.domain.dto.Content;
import com.rodest.elasticindex.service.ElasticIndexClient;
import com.rodest.elasticmodel.index.impl.TabnewsIndexModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
@Configuration
@AllArgsConstructor
public class JobConfiguration {

	private final RestTemplate restTemplate;

	private final ElasticIndexClient<TabnewsIndexModel> elasticIndexClient;

	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

		var counter = new AtomicInteger(1);

		return new StepBuilder("sampleStep", jobRepository)
			.tasklet((contribution, chunkContext) -> {

				var url = String.format("?page=%s", counter.getAndIncrement());

				var response = Stream.of(Objects.requireNonNull(
					restTemplate.getForObject(url, Content[].class)
				)).map(content -> {
					var indexModel = new TabnewsIndexModel();
					BeanUtils.copyProperties(content, indexModel);
					return indexModel;
				}).toList();

				var elasticResponse = elasticIndexClient.save(response);

				log.info("In page {}, it was saved {} with the ids: {}", counter.get(), elasticResponse.size(), elasticResponse);

				if(response.isEmpty()) return RepeatStatus.FINISHED;
				return RepeatStatus.CONTINUABLE;

			}, transactionManager)
			.build()
			;
	}
}
