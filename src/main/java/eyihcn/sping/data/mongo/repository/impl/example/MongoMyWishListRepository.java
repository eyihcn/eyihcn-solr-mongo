package eyihcn.sping.data.mongo.repository.impl.example;

import java.util.List;

import org.springframework.data.solr.example.repository.MyWishList;

import eyihcn.base.spring.data.mongo.repository.BaseMongoRepository;

public interface MongoMyWishListRepository extends BaseMongoRepository<MyWishList, Integer> {


	List<MyWishList> findByName(String name);

	List<MyWishList> findByNameLike(String name);
}
