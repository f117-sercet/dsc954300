package com.myo2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.eclipse.jdt.internal.compiler.ClassFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
private static final  SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyMMddHHmmss");
	private static final Random r=new Random();
public static String generateThumbnail(CommonsMultipartFile thumbnail,String targetAddr) {
	String realFileName=getRandomFlieName();
	String extension=getFileExtension(thumbnail);
	makeDirPath(targetAddr);
	String relativeAddr = targetAddr + realFileName + extension;
	File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
	try {
		Thumbnails.of(thumbnail.getInputStream()).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
		.outputQuality(0.8f).toFile(dest);
	}catch(IOException e) {
		e.printStackTrace();
	}
	
}

private static void makeDirPath(String targetAddr) {
	
	String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
	File dirPath=new File(realFileParentPath);
	if(!dirPath.exists()) {
		dirPath.mkdir();
	}
	// TODO Auto-generated method stub
	
}
/**
 *
 * @param thumbnail
 * @return
 */

private static String getFileExtension(CommonsMultipartFile cFile) {
String originalFileName=cFile.getOriginalFilename();

	return originalFileName.substring(originalFileName.lastIndexOf("."));
	
}
/**
 * ��������ļ�������ǰ������Сʱ��+��α�����
 * 	
 * @return
 */

public static String getRandomFlieName() {
	//��ȡ�漴��λ��
	int rannum=r.nextInt(89999)+10000;
	String nowTimeStr=sDateFormat.format(new Date());
	return nowTimeStr+rannum;
}
	public static void main(String[] args) throws IOException {
String basePath=Thread.currentThread().getContextClassLoader().getResource("").getProtocol();
	
			Thumbnails.of(new File("F:/java/java job/tu.jpg"))
			 .size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f)
			 .outputQuality(0.8f).toFile("\"F:/java/ava job/tu1.jpg");
		}
	
/**
 *storePath是文件的路径还是目录的路径
 *如果是文件路径则删除该文件
 *如果是目录路径则删除该目录下的所有文件
 */
 public static void deleteFileOrPath(String storePath) {
	 File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
	 if(fileOrPath.exists()) {
		 if(fileOrPath.isDirectory()) {
			 File files[]=fileOrPath.listFiles();
			 for(int i=0;i<files.length;i++)
				 files[i].delete();
		 }
		 
	 }
	 
                fileOrPath.delete();	 
 }
}
		

