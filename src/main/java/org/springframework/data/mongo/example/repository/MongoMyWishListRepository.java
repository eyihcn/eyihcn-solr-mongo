package org.springframework.data.mongo.example.repository;

import java.util.List;

import org.springframework.data.example.model.MyWishList;

import eyihcn.base.mongo.repository.BaseMongoRepository;

public interface MongoMyWishListRepository extends BaseMongoRepository<MyWishList, Integer> {


	List<MyWishList> findByName(String name);
}
