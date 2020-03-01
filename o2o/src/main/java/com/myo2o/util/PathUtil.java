package com.myo2o.util;

public class PathUtil {

	private static String seperator=System.getProperty("file.seperator");
	public static String getImgBasePath() {
	String os=System.getProperty("os.name");
	String basePath="";
	if(os.toLowerCase().startsWith("win")) {
		basePath="F:/java/java job";
		}
	else {
		basePath="/home/dsc/image/";
	}
	
       basePath=basePath.replace("/", seperator);
       return basePath;
       
}
	public static String getShopImgPath(long shopId) {
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath.replace("/", seperator);
	}
}

