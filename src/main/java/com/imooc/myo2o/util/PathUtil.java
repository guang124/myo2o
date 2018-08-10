package com.imooc.myo2o.util;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月2日 下午4:09:51 
 */

public class PathUtil {
	  /*
     * 根据不同的操作系统，设置储存图片文件不同的根目录
     */
	 private static String seperator = System.getProperty("file.separator");//获取文件分隔符
    public static String getImgBasePath() {

        String os =System.getProperty("os.name");//获取操作系统名称
        String basePath = "";
        if(os.toLowerCase().startsWith("win")) {
          basePath = "D:/projectdev/image/";    //根据自己的实际路径进行设置
        }else {
            basePath = "/home/o2o/image/";//根据自己的实际路径进行设置
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }
    
    /**
     * 根据不同的业务需求返回不同的子路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "upkoad/item/shop/"+ shopId + "/";
        return imagePath.replace("/", seperator);
    }
}


