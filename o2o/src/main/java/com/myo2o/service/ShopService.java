package com.myo2o.service;

import java.io.InputStream;

import com.myo2o.Exception.ShopOperationExcetion;
import com.myo2o.dto.ShopExecution;
import com.myo2o.entity.Shop;

public interface ShopService {
	/**
	 * 閫氳繃搴楅摵Id鑾峰彇搴楅摵淇℃伅
	 * @param shopId
	 * @return
	 */

Shop getbyShopId(long shopId);

/**
 * 鏇存柊搴楅摵淇℃伅
 * @param shop
 * @param shopImg
 * @return
 * @throws ShopOperationExcetion
 */
   ShopExecution modifySshop(Shop shop,InputStream shopImgInputStream,
	String fileName) throws ShopOperationExcetion;
	
	/**
	 * 娉ㄥ唽搴楅摵淇℃伅
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationExcetion
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,
	String fileName) throws ShopOperationExcetion;
	/**
	 * 根据shopCondition分页返回相应店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param PageSize
	 * @return
	 */
	public ShopExecution getshopList(Shop shopCondition,int pageIndex,int PageSize);
}
