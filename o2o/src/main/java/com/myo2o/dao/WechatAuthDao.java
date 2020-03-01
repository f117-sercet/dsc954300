package com.myo2o.dao;

import com.myo2o.entity.WeChatAuth;

public interface WechatAuthDao {
	/**
	 * 
	 * @param openId
	 * @return
	 */
	WeChatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WeChatAuth wechatAuth);

	/**
	 * 
	 * @param wechatAuthId
	 * @return
	 */
	int deleteWechatAuth(Long wechatAuthId);
}
