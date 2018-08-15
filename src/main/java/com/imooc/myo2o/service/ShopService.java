package com.imooc.myo2o.service;

import java.io.InputStream;

import com.imooc.myo2o.dto.ShopExecution;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.exception.ShopOperationException;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月7日 上午8:55:24 
 */

public interface ShopService {

	/**
	 * 通过shop id查询店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	Shop queryShopById(long shopId);

	/**
	 * 更新店铺信息，包括图片处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

	/**
	 * 新增店铺信息，包括图片处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

}
