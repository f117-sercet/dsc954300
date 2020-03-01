package com.myo2o.service;

import java.util.List;

import com.myo2o.Exception.ProductCategoryException;
import com.myo2o.Exception.ProductCategoryOperationException;
import com.myo2o.entity.ProductCategory;

public interface  ProductCategoryService{
/**
 * 获取商品信息
 * @param long shopId
 * @return List<ProductCategory>
 */
List<ProductCategory>getProductCategoryList(long shopId);

/**批量添加商品
 * @param productCategory
 * @return
 * @throws  ProductCategoryOperationException
 */
ProductCategoryException batchAddProductCategory(List<ProductCategory> productCategoryList)
         throws ProductCategoryOperationException;
/**
 *删除商品 
 * @param productCategoryId
 * @param shopId
 * @return
 * @throws ProductCategoryOperationException
 */
ProductCategoryException  deleteProductCategory(long productCategoryId,long shopId)
  throws ProductCategoryOperationException;
/**
 * 删除商品类别之前，将商品类别置为空0
 * @param productCategoryId
 * @return
 */
int updateProductCategoryToNull(long productCategoryId);
}
