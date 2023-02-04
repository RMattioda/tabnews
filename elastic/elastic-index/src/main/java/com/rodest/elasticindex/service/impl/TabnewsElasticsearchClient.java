package com.rodest.elasticindex.service.impl;

import com.rodest.elasticindex.repository.TabnewsElasticsearchRepository;
import com.rodest.elasticindex.service.ElasticIndexClient;
import com.rodest.elasticmodel.index.impl.TabnewsIndexModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TabnewsElasticsearchClient implements ElasticIndexClient<TabnewsIndexModel> {

	private final TabnewsElasticsearchRepository tabnewsElasticsearchRepository;

	@Override
	public List<String> save(List<TabnewsIndexModel> documents) {

		log.info("{} documents are being prepared to be saved", documents.size());

		var tabnewsModels = (List<TabnewsIndexModel>)
			tabnewsElasticsearchRepository.saveAll(documents);

		var ids = tabnewsModels.stream().map(TabnewsIndexModel::getId)
			.toList();

		log.info("Documents indexed successfully with ids: {}", ids);
//cbac1526-fb50-46af-8b8c-fcbfabd5742b
		return ids;
	}
}
