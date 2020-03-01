package com.myo2o.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.w3c.dom.ranges.RangeException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myo2o.Enum.ShopStateEnum;
import com.myo2o.dao.PersonInfoDao;
import com.myo2o.dto.ShopExecution;
import com.myo2o.entity.Area;
import com.myo2o.entity.PseronInfo;
import com.myo2o.entity.Shop;
import com.myo2o.service.AreaService;
import com.myo2o.service.ShopCategoryService;
import com.myo2o.service.ShopService;
import com.myo2o.util.HttpServletRequestUtil;
import com.myo2o.util.ImageUtil;
import com.myo2o.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagerController{ 
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService  areaService;
	@RequestMapping(value="/getshopmanagerinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object>getShopManagerInfo(HttpServletRequest request){
		 Map<String,Object> modelmap=new HashMap<String, Object>();
		 long shopId=HttpServletRequestUtil.getLong(request, "shopId");
		 if(shopId<=0) {
			 Object currentShopObj=request.getSession().getAttribute("currentshop");
			 if(currentShopObj==null) {
			 modelmap.put("redirect", true);
			 modelmap.put("url", "/o2o/shoplist");
			 }
		 else {
			 Shop currentShop=(Shop)currentShop;
			 modelmap.put("redirect", false);
			 modelmap.put("shopId", currentShop.getShopId());
		 
		 }
		 }else {
			 Shop currentShop=new Shop();
			currentShop.setShopId(shopId);
               request.getSession().setAttribute("currentShop", currentShop);
               modelmap.put("redirect",false);}


		 return modelmap;
		 }
	 
	@RequestMapping(value="/getshopList",method=RequestMethod.GET)
	@ResponseBody
    private Map<String,Object>getshopList(HttpServletRequest request){
		 Map<String,Object>modelmap=new HashMap<String, Object>();
		 PseronInfo owner=new PseronInfo();
		 owner.setUserId(1l);
		 request.getSession().setAttribute("ownwer",owner);
		 owner=(PseronInfo) request.getSession().getAttribute("owner");
		 List<Shop>shopList=new ArrayList<Shop>();
		 try {
		 Shop shopCondition=new Shop();
		 shopCondition.setOwner(owner);
		 ShopExecution se=shopService.getshopList(shopCondition, 0, 100);
		 modelmap.put("shopList",shopList);
			modelmap.put("owner",owner);
			modelmap.put("sucess",true);
	}catch(Exception e) {
		modelmap.put("success",false);
		modelmap.put("errMsg",e.getMessage());
	}
			return 	modelmap;
	}
			
	@RequestMapping(value="/modifyshop",method=RequestMethod.POST)
	@ResponseBody
private Map<String,Object>modifyShop(HttpServletRequest request){
	      Map<String,Object>modelMap=new HashMap<String,Object>();
		//1. ���ܲ�ת����Ӧ�Ĳ���
		String shopStr=HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop=mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.putIfAbsent("success", false);
			modelMap.putIfAbsent("errMsg", e.getMessage());
		
			return modelMap;
			}


CommonsMultipartFile shopImg=null;
CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(
		request.getSession().getServletContext());
if(commonsMultipartResolver.isMultipart(request)) {
	MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
    
    shopImg=(CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
}
	//2.修改店铺信息
if(shop!=null&&shop.getShopId()!=null) {
    ShopExecution se;
    if(shopImg==null) {
    	se=shopService.modifySshop(shop, null, null);
    }else {
  se=shopService.modifySshop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
    }if(se.getState()==ShopStateEnum.SUCCESS.getState()) {
    	modelMap.put("success",true);
    }else {
    	modelMap.putIfAbsent("success", false);
    	modelMap.putIfAbsent("errMsg", se.getStateInfo());
    }
    return modelMap;
}else {
	modelMap.putIfAbsent("success", false);
	modelMap.putIfAbsent("errMsg", "请输入店铺Id");
    return modelMap;
    
}
//3.���ؽ��
	}

	private static void inputStreamToFile(InputStream ins,File file) {
		FileOutputStream os=null;
		try {
		os=new FileOutputStream(file);
		int bytesRead=0;
		byte[] buffer=new byte[1024];
		while((bytesRead=ins.read(buffer))!=-1) {
			os.write(buffer,0,bytesRead);
		}
		}catch(Exception e) {
			throw new RuntimeException("����inputStreamToFile�����쳣"+e.getMessage());
		}finally {
			try {
				if(os!=null) {
					os.close();
				}
			if(ins!=null) {
				ins.close();}
			}catch(IOException e) {
				throw new RuntimeException("inputStreamToFile�رղ����쳣"+e.getMessage());
			}
		}
	}
}
			
	@RequestMapping(value="/getshopId",method=RequestMethod.GET)
	@ResponseBody
	private Map<String ,Object>getShopById(HttpServletRequest request){
		Map<String,Object>modelMap=new HashMap<String, Object>();
		Long shopId=HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId>-1) {
			try {
				Shop shop=shopService.getbyShopId(shopId);
				List<Area>areaList=areaService.getAreaList();
				modelMap.put("shop",shop);
				modelMap.put("areaList",areaList);
				modelMap.put("success", true);
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg" ,"empty shopId");
				
			}
	return modelMap;
	}
				
			
	
			

	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
private Map<String,Object>registerShop(HttpServletRequest request){
	      Map<String,Object>modelMap=new HashMap<String,Object>();
		//1. ���ܲ�ת����Ӧ�Ĳ���
		String shopStr=HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop=mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.putIfAbsent("success", false);
			modelMap.putIfAbsent("errMsg", e.getMessage());
		
			return modelMap;
			}


CommonsMultipartFile shopImg=null;
CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(
		request.getSession().getServletContext());
if(commonsMultipartResolver.isMultipart(request)) {
	MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
    
    shopImg=(CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
}
else {
	modelMap.putIfAbsent("success", false);
	modelMap.putIfAbsent("errMsg", "�ϴ�ͼƬ����Ϊ��");
    return modelMap;
          }
	
	//2.ע�����
if(shop!=null&&shopImg!=null) {
	PseronInfo owner=(PseronInfo) request.getSession().getAttribute("user");
   
    shop.setOwner(owner);
    File shopImgFile=new File(PathUtil.getImgBasePath()+ImageUtil.getRandomFlieName());
    try {
		inputStreamToFile(shopImg.getInputStream(), shopImgFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    ShopExecution se=shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
    if(se.getState()==ShopStateEnum.CHECK.getState()) {
    	modelMap.put("success",true);
    	//该用户可以操作的店铺列表
       List<Shop>shopList=(List<Shop>) request.getSession().getAttribute("shopList");
    	if(shopList==null||shopList.size()==0) {
    		shopList=new ArrayList<Shop>();
    		request.getSession().setAttribute("shopList", shopList);
    }else {
    	shopList.add(se.getShop());
    	request.getSession().setAttribute("shopList", shopList);
    }
    }	else {
    	modelMap.putIfAbsent("success", false);
    	modelMap.putIfAbsent("errMsg", se.getStateInfo());
    }
    return modelMap;
}else {
	modelMap.putIfAbsent("success", false);
	modelMap.putIfAbsent("errMsg", "�����������Ϣ");
    return modelMap;
    
}
//3.���ؽ��
	}

	private static void inputStreamToFile(InputStream ins,File file) {
		FileOutputStream os=null;
		try {
		os=new FileOutputStream(file);
		int bytesRead=0;
		byte[] buffer=new byte[1024];
		while((bytesRead=ins.read(buffer))!=-1) {
			os.write(buffer,0,bytesRead);
		}
		}catch(Exception e) {
			throw new RuntimeException("����inputStreamToFile�����쳣"+e.getMessage());
		}finally {
			try {
				if(os!=null) {
					os.close();
				}
			if(ins!=null) {
				ins.close();}
			}catch(IOException e) {
				throw new RuntimeException("inputStreamToFile�رղ����쳣"+e.getMessage());
			}
		}
	}
}
			

			
	
			
			
		
		
			
		
		
	
	

	
		




