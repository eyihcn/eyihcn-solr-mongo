package org.springframework.data.solr.example.repository;

import org.springframework.data.example.model.MyWishList;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;

public class SolrMyWishListRepository extends SimpleSolrRepository<MyWishList, Integer> {

}
