package com.rodest.elasticqueryclient.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ElasticQueryUtil{

	public Query getSearchQueryByFieldText(String field, String text) {

		var criteria = new Criteria(field)
			.is(text);

		return new CriteriaQuery(criteria);
	}

	public Query getSearchQueryForAll(Pageable pageable) {
		return NativeQuery.builder()
			.withQuery(query -> query.bool(boolquery -> boolquery.must(must -> must.matchAll(matchall -> matchall))))
			.withPageable(pageable)
			.build();
	}
}
