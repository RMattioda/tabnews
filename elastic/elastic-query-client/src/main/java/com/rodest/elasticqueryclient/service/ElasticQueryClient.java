package com.rodest.elasticqueryclient.service;

import com.rodest.elasticmodel.index.IndexModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ElasticQueryClient <T extends IndexModel>{

	T getIndexModelById(String id);

	List<T> getIndexModelByTitle(String title);

	Page<T> getAllIndexModels(Pageable pageable);
}
