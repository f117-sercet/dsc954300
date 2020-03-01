package com.myo2o.Exception;

import java.util.List;

import com.myo2o.Enum.ProductCategoryStateEnum;
import com.myo2o.entity.ProductCategory;

public class ProductCategoryException {
״̬
	private int state;

	private String statInfo;
	
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryException() {
		
	}
	
   public ProductCategoryException(ProductCategoryStateEnum stateEnum) {
	   this.state=stateEnum.getState();
	   this.statInfo=stateEnum.getStateInfo();
   }

   public ProductCategoryException(ProductCategoryStateEnum stateEnum,List<ProductCategory> productproductCategoryList) {
	   this.state=stateEnum.getState();
	   this.statInfo=stateEnum.getStateInfo();
	   this.productCategoryList=productCategoryList;
   
   }
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getStatInfo() {
	return statInfo;
}
public void setStatInfo(String statInfo) {
	this.statInfo = statInfo;
}
public List<ProductCategory> getProductCategoryList() {
	return productCategoryList;
}
public void setProductCategoryList(List<ProductCategory> productCategoryList) {
	this.productCategoryList = productCategoryList;
}
   
	

}
