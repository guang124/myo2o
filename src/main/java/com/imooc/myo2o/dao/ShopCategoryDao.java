package com.imooc.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.myo2o.entity.ShopCategory;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月12日 下午2:55:24 
 */

public interface ShopCategoryDao {
	/**
	 * 查询商品种类详情
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryCondition);

}


