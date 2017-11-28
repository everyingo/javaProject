package com.mine.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mine.model.Right;
import com.mine.service.RightService;

public class PowerUtil {

	public static Map<String,Right> rightMap;
	
	private RightService rightService;
	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}
	
	public void initMethod(){
		List<Right> rights = rightService.findAllEntities();
		rightMap = new HashMap<String, Right>();
		for (Right r : rights) {
			rightMap.put(r.getRightUrl(), r);
		}
		System.out.println("---------------------权限初始化完成！！----------------------------");
	}
	
	
}
