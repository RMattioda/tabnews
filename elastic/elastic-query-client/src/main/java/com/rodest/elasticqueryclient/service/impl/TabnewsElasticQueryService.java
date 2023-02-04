package com.rodest.elasticqueryclient.service.impl;

import com.rodest.configuration.ElasticConfigData;
import com.rodest.configuration.ElasticQueryConfigData;
import com.rodest.elasticmodel.index.impl.TabnewsIndexModel;
import com.rodest.elasticqueryclient.exception.ElasticQueryException;
import com.rodest.elasticqueryclient.service.ElasticQueryClient;
import com.rodest.elasticqueryclient.util.ElasticQueryUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class TabnewsElasticQueryService implements ElasticQueryClient<TabnewsIndexModel> {

	private final ElasticsearchOperations elasticsearchOperations;

	private final ElasticConfigData elasticConfigData;

	private final ElasticQueryConfigData queryConfigData;

	private final ElasticQueryUtil elasticQueryUtil;

	private final AbstractElasticsearchTemplate template;

	@Override
	public TabnewsIndexModel getIndexModelById(String id) {

		var search = elasticsearchOperations.get(id, TabnewsIndexModel.class,
			IndexCoordinates.of(elasticConfigData.getIndexName()));

		if(Objects.isNull(search)){
			throw new ElasticQueryException("No document found at elastic search with id " + id);
		}

		return search;
	}

	@Override
	public List<TabnewsIndexModel> getIndexModelByTitle(String title) {

		var query = elasticQueryUtil
			.getSearchQueryByFieldText(queryConfigData.getTitleField(), title);

		var search = elasticsearchOperations
			.search(query, TabnewsIndexModel.class, IndexCoordinates.of(elasticConfigData.getIndexName()));

		return search.get().map(SearchHit::getContent).toList();
	}

	@Override
	public Page<TabnewsIndexModel> getAllIndexModels(Pageable pageable) {
		Query queryForAll = elasticQueryUtil.getSearchQueryForAll(pageable);

		SearchHits<TabnewsIndexModel> search = elasticsearchOperations.search(queryForAll, TabnewsIndexModel.class,
			IndexCoordinates.of(elasticConfigData.getIndexName()));

		return new PageImpl<>
			(search.get().map(SearchHit::getContent).toList(), queryForAll.getPageable(),
				search.getTotalHits());
	}
}
