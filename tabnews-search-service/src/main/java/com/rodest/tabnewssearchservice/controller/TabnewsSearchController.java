package com.rodest.tabnewssearchservice.controller;

import com.rodest.elasticmodel.index.impl.TabnewsIndexModel;
import com.rodest.tabnewssearchservice.service.TabnewsQueryService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("tabnews")
public class TabnewsSearchController {

	private final TabnewsQueryService tabnewsQueryService;

	@GetMapping("/{id}")
	public ResponseEntity<TabnewsIndexModel> searchById(@PathVariable String id){
		return ResponseEntity.ok(tabnewsQueryService.findById(id));
	}

	@GetMapping("/filter")
	public ResponseEntity<List<TabnewsIndexModel>> filter(@RequestParam String title){

		return ResponseEntity.ok(tabnewsQueryService.findFilter(title));
	}

	@GetMapping
	public ResponseEntity<Page<TabnewsIndexModel>> filter(@PageableDefault Pageable pageable){

		return ResponseEntity.ok(tabnewsQueryService.findAll(pageable));
	}
}
