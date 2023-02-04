package com.rodest.tabnewssearchservice.service;

import com.rodest.elasticmodel.index.impl.TabnewsIndexModel;
import com.rodest.elasticqueryclient.service.ElasticQueryClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TabnewsQueryService {

	private final ElasticQueryClient<TabnewsIndexModel> queryClient;

	public TabnewsIndexModel findById(String id){
		return queryClient.getIndexModelById(id);
	}

	public List<TabnewsIndexModel> findFilter(String title) {
		return queryClient.getIndexModelByTitle(title);
	}

	public Page<TabnewsIndexModel> findAll(Pageable pageable) {
		return queryClient.getAllIndexModels(pageable);
	}
}
