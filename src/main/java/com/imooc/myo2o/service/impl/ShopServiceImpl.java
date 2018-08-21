package com.imooc.myo2o.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.imooc.myo2o.dao.ShopDao;
import com.imooc.myo2o.dto.ShopExecution;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.enums.ShopStateEnum;
import com.imooc.myo2o.exception.ShopOperationException;
import com.imooc.myo2o.service.ShopService;
import com.imooc.myo2o.util.ImageUtil;
import com.imooc.myo2o.util.PathUtil;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月7日 上午8:54:23 
 */

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException{
		// 空值判断
		if (ObjectUtils.isEmpty(shop)) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给店铺初始化值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("创建店铺失败");
			} else {
				if (shopImgInputStream != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImgInputStream,fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}

				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName,dest);
		shop.setShopImg(shopImgAddr);

	}

	@Override
	public Shop queryShopById(long shopId) {
		return shopDao.queryShopById(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException {
		//1. 判断是否需要处理图片.
		if (StringUtils.isEmpty(shop)&&StringUtils.isEmpty(shop.getShopId())) {
			
		}
		//2. 更新图片信息 
		return null;
	}

}
