package com.imooc.myo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.dto.ShopExecution;
import com.imooc.myo2o.entity.Area;
import com.imooc.myo2o.entity.PersonInfo;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.entity.ShopCategory;
import com.imooc.myo2o.enums.ShopStateEnum;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月11日 下午2:11:50 
 */

public class ShopserviceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
		Area area = new Area();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		area.setAreaId(3L);
		owner.setUserId(8l);
		shopCategory.setShopCategoryId(20L);

		shop.setShopId(32L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺5");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("D://laotie.jpg");
		ShopExecution se = shopService.addShop(shop, shopImg);

		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());

	}

}

