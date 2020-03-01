package com.myo2o.entity;

import java.util.Date;

public class ShopCategory {
	private long shopCategoryId;
	private String shopCategoryName;
	private String shopDesc;
	private String shopCategoryImg;
	private Integer proority;
	private Date    createTime;
	private Date    lastEditTime;
	private ShopCategory parent;
	public long getShopCategoryId() {
		return shopCategoryId;
	}
	public void setShopCategoryId(long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}
	public String getShopCategoryName() {
		return shopCategoryName;
	}
	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopCategoryImg() {
		return shopCategoryImg;
	}
	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
	}
	public Integer getProority() {
		return proority;
	}
	public void setProority(Integer proority) {
		this.proority = proority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public ShopCategory getParent() {
		return parent;
	}
	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}
	

}
