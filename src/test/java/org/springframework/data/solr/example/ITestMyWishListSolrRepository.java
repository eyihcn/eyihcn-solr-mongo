package org.springframework.data.solr.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.example.repository.SolrMyWishListRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

import eyihcn.data.example.model.MyWishList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/springframework/data/solr/example/applicationContext.xml")
public class ITestMyWishListSolrRepository {

	@Autowired
	SolrMyWishListRepository repo;

	@Test
	public void testDelete() {
		repo.delete(0);
	}

	@Test
	public void testFind() {
		Pageable pageable = new SolrPageRequest(0, 1);
		Page<MyWishList> findAll = repo.findAll(pageable);
		System.out.println(findAll.getContent().size());
	}

	@Test
	public void testCRUD() {
		Gson gson = new Gson();
		MyWishList entity = new MyWishList();
		entity.setId(2);
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
