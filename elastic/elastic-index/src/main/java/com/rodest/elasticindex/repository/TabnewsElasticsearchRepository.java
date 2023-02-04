package com.rodest.elasticindex.repository;

import com.rodest.elasticmodel.index.impl.TabnewsIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabnewsElasticsearchRepository
	extends ElasticsearchRepository<TabnewsIndexModel, String> {

}
