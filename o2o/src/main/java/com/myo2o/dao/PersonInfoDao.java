package com.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myo2o.entity.PseronInfo;

public interface PersonInfoDao {

	/**
	 * 
	 * @param personInfoCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<PseronInfo> queryPersonInfoList(
			@Param("personInfoCondition") PseronInfo personInfoCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 
	 * @param personInfoCondition
	 * @return
	 */
	int queryPersonInfoCount(
			@Param("personInfoCondition") PseronInfo personInfoCondition);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	PseronInfo queryPersonInfoById(long userId);

	/**
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int insertPersonInfo(PseronInfo personInfo);

	/**
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int updatePersonInfo(PseronInfo personInfo);

	/**
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int deletePersonInfo(long userId);
}
