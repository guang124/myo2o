package com.imooc.myo2o.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.entity.Area;

/* 
 * @author yuan 
 * @version 1.0 
 * @date 2018年7月31日 下午3:12:58 
 */
public class AreaServiceTest extends BaseTest {
	@Autowired 
	private AreaService areaService;
	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		assertEquals("东苑", areaList.get(0).getAreaName());
		//assertEquals(5,areaList.size());
	}

}


