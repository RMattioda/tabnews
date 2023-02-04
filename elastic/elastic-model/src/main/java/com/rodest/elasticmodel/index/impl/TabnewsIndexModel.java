package com.rodest.elasticmodel.index.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rodest.elasticmodel.index.IndexModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "tabnews")
public class TabnewsIndexModel implements IndexModel {

	@Id
	private String id;

	@JsonProperty("owner_id")
	private String ownerId;

	@JsonProperty("parent_id")
	private String parentId;

	@JsonProperty("slug")
	private String slug;

	@JsonProperty("title")
	private String title;

	@JsonProperty("status")
	private String status;

	@JsonProperty("source_url")
	private String sourceUrl;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;

	@JsonProperty("published_at")
	private LocalDateTime publishedAt;

	@JsonProperty("deleted_at")
	private LocalDateTime deletedAt;

	@JsonProperty("tabcoins")
	private Long tabCoins;

	@JsonProperty("owner_username")
	private String ownerUsername;

	@JsonProperty("children_deep_count")
	private Long childrenDeepCount;
}
