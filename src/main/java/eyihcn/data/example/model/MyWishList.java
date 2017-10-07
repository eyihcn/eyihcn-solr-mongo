package eyihcn.data.example.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.google.gson.Gson;

import eyihcn.base.entity.BaseEntity;

@SolrDocument(collection = "mywishlist")
public class MyWishList extends BaseEntity<Integer> implements SearchableMyWishList {

	Gson gson = new Gson();

	private static final long serialVersionUID = -4290648075971100781L;

	@Field(ID_FIELD)
	private Integer id;

	@Field(NAME_FIELD)
	private String name;

	@Field(PRICE_FIELD)
	private Float price;

	// @Field(SKU_TO_QTY_LIST_FIELD)
	private List<Map<String, Object>> skuToQtyList; // 商品信息

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Map<String, Object>> getSkuToQtyList() {
		return skuToQtyList;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	// @Transient

	private String skuToQtyListJson; // 商品信息

	public String getSkuToQtyListJson() {
		return skuToQtyListJson;
	}

	@SuppressWarnings("unchecked")
	@Field(SKU_TO_QTY_LIST_FIELD)
	public void setSkuToQtyListJson(String skuToQtyListJson) {
		System.out.println(".....setSkuToQtyListJson........");
		this.skuToQtyListJson = skuToQtyListJson;
		if (StringUtils.isNotBlank(skuToQtyListJson)) {
			this.skuToQtyList = gson.fromJson(skuToQtyListJson, List.class);
		} else {
			this.skuToQtyList = null;
		}
	}

	public void setSkuToQtyList(List<Map<String, Object>> skuToQtyList) {
		System.out.println(".....setSkuToQtyList........");
		if (CollectionUtils.isEmpty(skuToQtyList)) {
			this.skuToQtyListJson = null;
		} else {
			this.skuToQtyListJson = gson.toJson(skuToQtyList);
		}
		this.skuToQtyList = skuToQtyList;
	}


}
