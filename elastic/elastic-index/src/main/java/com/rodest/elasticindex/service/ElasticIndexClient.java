package com.rodest.elasticindex.service;

import com.rodest.elasticmodel.index.IndexModel;

import java.util.List;

public interface ElasticIndexClient<T extends IndexModel> {

    List<String> save(List<T> documents);
}
