package com.myo2o.entity;

import java.util.Date;

public class WeChatAuth {
	private long wechatAuthId;
	private String openId;
	private Date creatTime;
	private PseronInfo personInfo;
	public long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public PseronInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PseronInfo personInfo) {
		this.personInfo = personInfo;
	}
	

}
