package com.myo2o.entity;

import java.util.Date;

public class Shop {
private Long shopId;
private String shopName;
private String shopDesc; 
private String shopAddr; 
private String phone; 
private String shopImg; 
private Integer priority;
private Date    createTime;
private Date    lastEditTime;
//-1��ʾ�����ã�0��ʾ����У� 1����
private Integer  enableStatus;
private Area area;
private PseronInfo owner;
private String  advice;
private ShopCategory shopCategory;
public Long getShopId() {
	return shopId;
}
public void setShopId(Long shopId) {
	this.shopId = shopId;
}
public String getShopName() {
	return shopName;
}
public void setShopName(String shopName) {
	this.shopName = shopName;
}
public String getShopDesc() {
	return shopDesc;
}
public void setShopDesc(String shopDesc) {
	this.shopDesc = shopDesc;
}
public String getShopAddr() {
	return shopAddr;
}
public void setShopAddr(String shopAddr) {
	this.shopAddr = shopAddr;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getShopImg() {
	return shopImg;
}
public void setShopImg(String shopImg) {
	this.shopImg = shopImg;
}
public Integer getPriority() {
	return priority;
}
public void setPriority(Integer priority) {
	this.priority = priority;
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
public Integer getEnableStatus() {
	return enableStatus;
}
public void setEnableStatus(Integer enableStatus) {
	this.enableStatus = enableStatus;
}
public Area getArea() {
	return area;
}
public void setArea(Area area) {
	this.area = area;
}
public PseronInfo getOwner() {
	return owner;
}
public void setOwner(PseronInfo owner) {
	this.owner = owner;
}
public String getAdvice() {
	return advice;
}
public void setAdvice(String advice) {
	this.advice = advice;
}
public ShopCategory getShopCategory() {
	return shopCategory;
}
public void setShopCategory(ShopCategory shopCategory) {
	this.shopCategory = shopCategory;
}




}