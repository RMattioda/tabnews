package com.rodest.batch.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Content {

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
