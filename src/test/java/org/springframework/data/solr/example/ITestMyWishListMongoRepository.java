package org.springframework.data.solr.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.example.model.MyWishList;
import org.springframework.data.mongo.example.repository.MongoMyWishListRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/springframework/data/solr/example/applicationContext.xml")
public class ITestMyWishListMongoRepository {

	@Autowired
	MongoMyWishListRepository repo;

	@Test
	public void testCRUD() {
		Gson gson = new Gson();
		MyWishList entity = new MyWishList();
		entity.setId(1);
		List<Map<String, Object>> skuToQtyList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sku", "sku1");
		map.put("qty", 2);
		skuToQtyList.add(map);
		entity.setSkuToQtyList(skuToQtyList);
		entity.setSkuToQtyListJson(gson.toJson(skuToQtyList));
		repo.save(entity);
	}
}
