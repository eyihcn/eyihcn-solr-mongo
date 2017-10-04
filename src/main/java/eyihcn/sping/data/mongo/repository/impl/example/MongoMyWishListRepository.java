package eyihcn.sping.data.mongo.repository.impl.example;

import java.util.List;

import eyihcn.base.spring.data.mongo.repository.BaseMongoRepository;
import eyihcn.data.example.model.MyWishList;

public interface MongoMyWishListRepository extends BaseMongoRepository<MyWishList, Integer> {


	List<MyWishList> findByName(String name);
}
