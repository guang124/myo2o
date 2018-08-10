package com.imooc.myo2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月9日 下午8:50:55 
 */
@Controller
@RequestMapping(value="shopadmin",method={RequestMethod.GET})
public class ShopAdminController {
	@RequestMapping(value="shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}

}


