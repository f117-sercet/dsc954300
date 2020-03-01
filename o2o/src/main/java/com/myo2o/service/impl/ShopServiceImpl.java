package com.myo2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myo2o.Enum.ShopStateEnum;
import com.myo2o.Exception.ShopOperationExcetion;
import com.myo2o.dao.ShopDao;
import com.myo2o.dto.ShopExecution;
import com.myo2o.entity.Shop;
import com.myo2o.service.ShopService;
import com.myo2o.util.ImageUtil;
import com.myo2o.util.PageCalculator;
import com.myo2o.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
private ShopDao shopDao;
	public ShopExecution getshopList(Shop shopCondition,int pageIndex,int PageSize) {
		int rowIndex=PageCalculator.calculateRowIndex(pageIndex, PageSize);
	
	List<Shop>shopList=shopDao.queryShopList(shopCondition, rowIndex, PageSize);
	int count=shopDao.queryShopCount(shopCondition);
	ShopExecution se=new ShopExecution();
	if(shopList!=null) {
	se.setShopList(shopList);
	se.setCount(count);
	}else {
		se.setState(ShopStateEnum.INNER_ERROR.getState());
	}
	return se;
	
	}
	@Transactional
	public ShopExecution addShop(Shop shop, File shopImg) {
	//空值判断
	if(shop==null) {
	return new ShopExecution(ShopStateEnum.Null_SHOP);
	}
	try {
		//给店铺信息赋初值
		shop.setEnableStatus(0);
		shop.setCreateTime(new Date());
	    shop.setLastEditTime(new Date());
		//添加店铺信息
	     int effectedNum=shopDao.insertShop(shop);
		if(effectedNum<=0) {
			throw new RuntimeException("店铺创建失败");
			}else {
				if(shopImg!=null) {
				//存储图片	
				try {
					
				addShopImg(shop,shopImg);
				shop.getShopImg();
				}catch(Exception e ) {
					throw new RuntimeException("addShopImgerror"+e.getMessage());
				}
				//更新店铺的图片地址
				effectedNum=shopDao.updateShop(shop);
				if(effectedNum<=0) {
					throw new RuntimeException("更新图片地址失败");
					
				}
				}
			}
		 	 

}catch(Exception e) {
	throw new RuntimeException("add shop error:"+e.getMessage());
	
}
return new ShopExecution(ShopStateEnum.CHECK,shop) ;
}
	private void addShopImg(Shop shop, File shopImg) {
		//获取Shop的相对子路径
		String dest=PathUtil.getShopImgPath(shop.getShopId());
		String shopImgAddr=ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
		
		
		}   
public Shop getByShopId(long shopId) {
	return ShopDao.queryByShopId(shopId);
}
public ShopExecution modifySshop(Shop shop,InputStream shopImgInputStream,
	String fileName) throws ShopOperationExcetion{
	if(shop==null||shop.getShopId()==null) {
		return new ShopExecution(ShopStateEnum.Null_SHOP);
}else {
	//1.判断是否需要处理图片
	try {
		if(shopImgInputStream!=null&&fileName!=null&&!"".contentEquals(fileName)) {
			Shop tempShop=shopDao.queryByShopId(shop.getShopId());
			if(tempShop.getShopImg()!=null) {
				ImageUtil.deleteFileOrPath(tempShop.getShopImg());
			}
		addShop(shop, shopImgInputStream,fileName);
		}
	//2.更新店铺信息
		shop.setLastEditTime(new Date());
		int effectedNum=shopDao.updateShop(shop);
		if( effectedNum<=0) {
			return new ShopExecution(ShopStateEnum.INNER_ERROR);
			
		}else {
			shop=shopDao.queryByShopId(shop.getShopId());
			return new ShopExecution(ShopStateEnum.SUCCESS,shop);
}
 }catch(Exception e){
	 throw new ShopOperationExcetion("modifyShop error"+e.getMessage());
 }
}
	
	
	
	
}
	
}
