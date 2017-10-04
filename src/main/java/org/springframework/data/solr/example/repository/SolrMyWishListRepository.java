package org.springframework.data.solr.example.repository;

import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;

import eyihcn.data.example.model.MyWishList;

public class SolrMyWishListRepository extends SimpleSolrRepository<MyWishList, Integer> {

	public SolrMyWishListRepository(SolrOperations solrOperations) {
		this(solrOperations, MyWishList.class);
	}
	
	public SolrMyWishListRepository(SolrOperations solrOperations, Class<MyWishList> entityClass) {
		super(solrOperations, entityClass);
	}

}
