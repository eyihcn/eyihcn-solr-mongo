package eyihcn.data.example.model;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;

import com.google.gson.Gson;

import eyihcn.base.entity.BaseEntity;

public class MyWishList extends BaseEntity<Integer> implements SearchableMyWishList {

	private static final long serialVersionUID = 5692384326770093398L;

	Gson gson = new Gson();

	@Field(ID_FIELD)
	private Integer id;

	@Field(NAME_FIELD)
	private String name;

	@Field(PRICE_FIELD)
	private Float price;

	@Field(SKU_TO_QTY_LIST_FIELD)
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
	@Field(SKU_TO_QTY_LIST_JSON_FIELD)
	private String skuToQtyListJson; // 商品信息

	public String getSkuToQtyListJson() {
		return skuToQtyListJson;
	}

	public void setSkuToQtyListJson(String skuToQtyListJson) {
		this.skuToQtyListJson = skuToQtyListJson;
	}

	public void setSkuToQtyList(List<Map<String, Object>> skuToQtyList) {
		this.skuToQtyList = skuToQtyList;
	}

}
