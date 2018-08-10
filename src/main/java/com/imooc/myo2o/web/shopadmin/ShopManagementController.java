package com.imooc.myo2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
import com.imooc.myo2o.entity.PersonInfo;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.enums.ShopStateEnum;
import com.imooc.myo2o.service.ShopService;
import com.imooc.myo2o.util.HttpServletRequestUtil;
import com.imooc.myo2o.util.ImageUtil;
import com.imooc.myo2o.util.PathUtil;

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
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//1.接收图片并转化相应的参数，包括店铺信息以及图片信息
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		//2.注册店铺
		if (shop != null && shopImg != null) {
				PersonInfo owner = new PersonInfo();
				owner.setUserId(8L);
				shop.setOwner(owner);
				File shopImgFile = new File(PathUtil.getImgBasePath()+ImageUtil.getRandomFileName());
				try {
					inputStreamToFile(shopImg.getInputStream(),shopImgFile);
				} catch (IOException e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
					return modelMap;
				}
				ShopExecution se = shopService.addShop(shop, shopImgFile);
				if (se.getState()==ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
				return modelMap;

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		
	}
	private static void inputStreamToFile(InputStream ins ,File file) {
		FileOutputStream os =null;
		try {
			os=new FileOutputStream(file);
			int bytesRead=0;
			byte[] buffer = new byte[1024];
			//读取ins里的数据流到buffer，只要没有读完，就不会是-1
			while ((bytesRead=ins.read(buffer))!=-1) {
				os.write(buffer,0,bytesRead);
			}
		} catch (Exception e) {
			throw new RuntimeException("调用inputStreamToFile失败");
		}finally{
			
				try {
					if (os!=null) {
					os.close();
					}
					if(ins!=null){
						ins.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				
			}
		}
		
		
	}


}


