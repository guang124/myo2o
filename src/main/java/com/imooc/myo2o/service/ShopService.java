package com.imooc.myo2o.service;

import java.io.File;

import com.imooc.myo2o.dto.ShopExecution;
import com.imooc.myo2o.entity.Shop;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月7日 上午8:55:24 
 */

public interface ShopService {
	ShopExecution addShop(Shop shop, File shopImg);

}


