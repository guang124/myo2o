package com.imooc.myo2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年8月2日 上午11:31:59 
 */

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random(); 

 /**
  * 处理缩略图，并返回新生成图片的相对值路径
  * @param shopImgInputStream
  * @param fileName
  * @param targetAddr
  * @return
  */
    public static String generateThumbnail(InputStream  shopImgInputStream,String fileName ,String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr  =targetAddr +realFileName + extension;
        System.out.println("relativeAddr::"+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        System.out.println("dest::"+PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(shopImgInputStream).size(200, 200)
            .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/water.jpg")),0.25f)
            .outputQuality(0.8f).toFile(dest);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录，即/home/work/o2o/xxx.jpg,
     * 那么 home work o2o 这三个文件夹都得自动创建
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        // TODO Auto-generated method stub
        String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }


    /**
     * 生成随机文件名，当前年月日小时分钟秒+五位随机数
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }
    /**
     * 获取输入文件流的扩展名
     * @param args
     * @throws IOException
     */
    private static String getFileExtension(String fileName) {
        String originalFileName = fileName;
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }
    /**
     * storePath可能是文件或目录的路径，
     * 如果是文件路径则删除该文件，
     * 如果是目录路径则删除该目录下的所有文件
     * 
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
    	File fileOrPash = new File(PathUtil.getImgBasePath()+ storePath);
    	if (fileOrPash.exists()) {
    		if (fileOrPash.isDirectory()) {
    			File file[]=fileOrPash.listFiles();
    			for (int i = 0; i < file.length; i++) {
					file[i].delete();
				}
				
			}
    		fileOrPash.delete();
			
		}
		
	}
}

