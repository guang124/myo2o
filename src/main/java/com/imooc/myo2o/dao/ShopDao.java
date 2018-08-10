package com.imooc.myo2o.dao;

import com.imooc.myo2o.entity.Shop;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月1日 下午7:20:49 
 */

public interface ShopDao {
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺信息
	 */
	int updateShop(Shop shop);
}


