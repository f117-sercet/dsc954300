package com.myo2o.entity;

import java.util.Date;

public class PseronInfo {
private Long userId;
private String name;
private String  profileImg;
private String email;
private String gender;
private String enableStatue;
//1.顾客 2.店家 3.超级管理员
private Integer userType;
private Date creaTime;
private Date lastEditTime;
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getProfileImg() {
	return profileImg;
}
public void setProfileImg(String profileImg) {
	this.profileImg = profileImg;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEnableStatue() {
	return enableStatue;
}
public void setEnableStatue(String enableStatue) {
	this.enableStatue = enableStatue;
}
public Integer getUserType() {
	return userType;
}
public void setUserType(Integer userType) {
	this.userType = userType;
}
public Date getCreaTime() {
	return creaTime;
}
public void setCreaTime(Date creaTime) {
	this.creaTime = creaTime;
}
public Date getLastEditTime() {
	return lastEditTime;
}
public void setLastEditTime(Date lastEditTime) {
	this.lastEditTime = lastEditTime;
}

}
