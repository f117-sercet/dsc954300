package com.myo2o.entity;

import java.util.Date;

public class ProductImg {

	private Long   prodouctImgId;
	private String  imgAddr;
	private Integer priority;
	private  Date   creatTime;
	private Long    productId;
	public Long getProdouctImgId() {
		return prodouctImgId;
	}
	public void setProdouctImgId(Long prodouctImgId) {
		this.prodouctImgId = prodouctImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
