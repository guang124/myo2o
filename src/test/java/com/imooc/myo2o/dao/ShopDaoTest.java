package com.imooc.myo2o.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.entity.Area;
import com.imooc.myo2o.entity.PersonInfo;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.entity.ShopCategory;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月2日 上午10:03:40 
 */

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testquerybyId() throws Exception {
		long shopId=15l;
		Shop shop = shopDao.queryShopById(shopId);
		System.out.println("AreaName"+shop.getArea().getAreaId()+shop.getArea().getAreaName());
		System.out.println("shopCategoryName"+shop.getShopCategory().getShopCategoryName()+shop.getShopCategory().getShopCategoryId());
	}
	@Test
	@Ignore
	public void testInsertShop() throws Exception {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(8L);
		area.setAreaId(3L);
		shopCategory.setShopCategoryId(10L);

		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);

	}
	@Test
	@Ignore
	public void testUpdateShop() throws Exception {
		Shop shop = new Shop();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		area.setAreaId(3L);
		shopCategory.setShopCategoryId(20L);
		
		shop.setShopId(32L);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺2");
		shop.setShopDesc("test2");
		shop.setShopAddr("test2");
		shop.setPhone("test2");
		shop.setShopImg("test2");
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核通过");
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}
