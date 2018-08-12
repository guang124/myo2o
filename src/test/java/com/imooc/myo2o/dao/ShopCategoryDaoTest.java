package com.imooc.myo2o.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.entity.ShopCategory;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月12日 下午3:07:40 
 */

public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryategoryDao() throws Exception {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(18, shopCategoryList.size());
	}

}


