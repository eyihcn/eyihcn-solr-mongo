package org.springframework.data.solr.example.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import eyihcn.base.spring.data.solr.repository.BaseSolrRepository;
import eyihcn.data.example.model.MyWishList;


@Repository
public interface SolrMyWishListRepository extends BaseSolrRepository<MyWishList, Integer> {

	List<MyWishList> findListByName(String name);

	MyWishList findOneByNameLike(String name);
}

