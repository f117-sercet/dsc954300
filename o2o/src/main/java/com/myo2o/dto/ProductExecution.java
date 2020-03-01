package com.myo2o.dto;

import java.util.List;

import com.myo2o.Enum.ProductCategoryStateEnum;
import com.myo2o.Enum.ProductStateEnum;
import com.myo2o.entity.Product;

public class ProductExecution {

	private int state;


	private String stateInfo;


	private int count;


	private Product product;


	private List<Product> productList;

	public ProductExecution() {
	}


	public ProductExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 鎴愬姛鐨勬瀯閫犲櫒
	public ProductExecution(ProductStateEnum stateEnum, Product product) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.product = product;
	}

	// 鎴愬姛鐨勬瀯閫犲櫒
	public ProductExecution(ProductStateEnum stateEnum,
			List<Product> productList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
