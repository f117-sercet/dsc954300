package com.myo2o.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.logging.jdbc.ResultSetLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myo2o.Enum.ProductCategoryStateEnum;
import com.myo2o.Exception.ProductCategoryException;
import com.myo2o.Exception.ProductCategoryOperationException;
import com.myo2o.dao.ProductCategoryDao;
import com.myo2o.dto.Result;
import com.myo2o.entity.ProductCategory;
import com.myo2o.entity.Shop;
import com.myo2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagerController {
@Autowired
private ProductCategoryService productcategoryService;
@Autowired
private ProductCategoryDao productCategoryDao;
@RequestMapping(value = "/removeproductcategorys", method = RequestMethod.POST)
@ResponseBody
private Map<String, Object> removedProductCategorys(
		@RequestBody List<ProductCategory> productCategoryList,
		HttpServletRequest request) {
	Map<String, Object> modelMap = new HashMap<String, Object>();
	Shop currentShop = (Shop) request.getSession().getAttribute(
			"currentShop");
	for (ProductCategory pc : productCategoryList) {
		pc.setShopId(currentShop.getShopId());
	}
	if (productCategoryList != null && productCategoryList.size() > 0) {
		try {
			ProductCategoryException pe = productcategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
			if (pe.getState() == ProductCategoryStateEnum.SUCCESS
					.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStatInfo());
			}
		} catch (RuntimeException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}

	} else {
		modelMap.put("success", false);
		modelMap.put("errMsg", "请至少选择一个类别");
	}
	return modelMap;
}


@Transactional
ProductCategoryException batchAddProductCategory(List<ProductCategory> productCategoryList)
        throws ProductCategoryOperationException{
	if(productCategoryList!=null&&productCategoryList.size()>0) {
		try {
			int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
			if(effectedNum<=0) {
				throw new ProductCategoryOperationException("店铺类别创建失败");
			}else {
				return new ProductCategoryException(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e) {
			throw new ProductCategoryOperationException("batchAddProductCategory"+e.getMessage());
		}
	}else {
		return new ProductCategoryException(ProductCategoryStateEnum.EMPTY_LIST);
	}
}
 
@RequestMapping(value = "/addproductcategorys",method = RequestMethod.POST)
@ResponseBody
private Map<String,Object> addProductCategory(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
Map<String,Object>modelMap=new HashMap<String, Object>();
Shop currentShop=(Shop) request.getSession().getAttribute("currentShop");
for(ProductCategory pc:productCategoryList) {
	pc.setShopId(currentShop.getShopId());
}
if(productCategoryList!=null&&productCategoryList.size()>0) {
	try {
		ProductCategoryException pe=productcategoryService.batchAddProductCategory(productCategoryList);
	   if(pe.getState()==ProductCategoryStateEnum.SUCCESS.getState()) {
		   modelMap.put("success", true);
	   }else {
		   modelMap.put("success", false);
		   modelMap.put("errMsg", pe.getStatInfo());}
	   
	   }catch(ProductCategoryOperationException e) {
		   modelMap.put("success", false);
		   modelMap.put("errMsg", e.toString());
		   return modelMap;
	   }
	   }
	   else {
		   modelMap.put("success", false);
		   modelMap.put("errMsg","请至少输入一个商品类别");
	   }


 return modelMap;
}

@RequestMapping(value="/getproductcategorylist",method=RequestMethod.GET)
@ResponseBody
private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
	Shop shop=new Shop();
	shop.setShopId(1l);
	request.getSession().setAttribute("currentShop", shop);
	Shop currentShop=(Shop) request.getSession().getAttribute("currentShop");
	List<ProductCategory>list=null;
	if(currentShop!=null&&currentShop.getShopId()>0) {
		list=productcategoryService.getProductCategoryList(currentShop.getShopId());
		return new Result<List<ProductCategory>>(true, list);
	}else {
		ProductCategoryStateEnum ps=ProductCategoryStateEnum.INNER_ERROR;
	return new Result<List<ProductCategory>>(false, ps.getState(),ps.getStateInfo());
	}
	
	
	
	
}

}
