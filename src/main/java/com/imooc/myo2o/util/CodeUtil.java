package com.imooc.myo2o.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月12日 下午7:40:32 
 */

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {

		//获取图片中的验证码
		String verifyCodeExpected=(String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//获取输入的验证码
		String verifyCodeActual=HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if (StringUtils.isEmpty(verifyCodeExpected) || verifyCodeExpected.equals(verifyCodeActual)) {
			return  false;
		}
		return true;
		
	}

}


