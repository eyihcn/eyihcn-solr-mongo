/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.solr.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import eyihcn.data.example.model.MyWishList;


/**
 * @author Christoph Strobl
 */
public abstract class AbstractSolrIntegrationTest {
	Gson gson = new Gson();

	protected List<MyWishList> createMyWishListList(int nrMyWishLists) {
		List<MyWishList> MyWishLists = new ArrayList<MyWishList>(nrMyWishLists);
		for (int i = 0; i < nrMyWishLists; i++) {
			MyWishLists.add(createMyWishList(i));
    }
		return MyWishLists;
  }

	protected MyWishList createMyWishList(int id) {
		MyWishList MyWishList = new MyWishList();
		MyWishList.setId(id);
		List<Map<String, Object>> skuToQtyList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sku", "sku" + id);
		map.put("qty", id);
		skuToQtyList.add(map);
		MyWishList.setSkuToQtyList(skuToQtyList);
		MyWishList.setSkuToQtyListJson(gson.toJson(skuToQtyList));
		MyWishList.setName("MyWishList|" + id);
		MyWishList.setPrice((float) id * 100);

		return MyWishList;
  }
}
