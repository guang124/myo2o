package com.imooc.myo2o.exception;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月7日 上午8:52:27 
 */

public class ShopOperationException extends RuntimeException{
	/**
	 * shop操作异常都抛到这里
	 */
	private static final long serialVersionUID = -6691229607054377248L;

	public ShopOperationException(String msg) {
        super(msg);
	}

}


