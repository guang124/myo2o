package com.imooc.myo2o.web.shopadmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.myo2o.dto.ShopExecution;
import com.imooc.myo2o.entity.Area;
import com.imooc.myo2o.entity.PersonInfo;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.entity.ShopCategory;
import com.imooc.myo2o.enums.ShopStateEnum;
import com.imooc.myo2o.service.AreaService;
import com.imooc.myo2o.service.ShopCategoryService;
import com.imooc.myo2o.service.ShopService;
import com.imooc.myo2o.util.CodeUtil;
import com.imooc.myo2o.util.HttpServletRequestUtil;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月7日 上午11:47:03 
 */
@Controller
@RequestMapping("shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	/**
	 * 获取Area和shopCategory信息，初始化商铺注册页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = null;
		List<Area> areaList = null;
		try {
			shopCategoryList = shopCategoryService.queryShopCategory(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		return modelMap;

	}
/**
 * 处理商铺注册信息
 * @param request
 * @return
 */
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//验证码
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码输入不正确");
			return modelMap;
		}
		// 1.接收图片并转化相应的参数，包括店铺信息以及图片信息
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			e.printStackTrace();
			return modelMap;
		}
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
				.getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		
		// 2.注册店铺
		if (shop != null && shopImg != null) {
			PersonInfo owner = new PersonInfo();
			//Session TODO
			owner.setUserId(8L);
			shop.setOwner(owner);
			
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
			
			return modelMap;

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}

	}

}
