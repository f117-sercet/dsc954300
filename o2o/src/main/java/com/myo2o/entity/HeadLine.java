package com.myo2o.entity;

import java.util.Date;

public class HeadLine {
	private Long LineId;
	private String LineName;
	private String LineLink;
	private String LineImg;
	//0 不可用   1可用
	private Integer enableStatus;
	private Date    creatTime;
	private Date    lastEditTime;
	public Long getLineId() {
		return LineId;
	}
	public void setLineId(Long lineId) {
		LineId = lineId;
	}
	public String getLineName() {
		return LineName;
	}
	public void setLineName(String lineName) {
		LineName = lineName;
	}
	public String getLineLink() {
		return LineLink;
	}
	public void setLineLink(String lineLink) {
		LineLink = lineLink;
	}
	public String getLineImg() {
		return LineImg;
	}
	public void setLineImg(String lineImg) {
		LineImg = lineImg;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	

	
}
