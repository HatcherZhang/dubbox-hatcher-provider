package com.hatcher.cvte.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hatcher.cvte.model.Dept;
import com.hatcher.cvte.model.vo.DeptVo;

public class TreeUtils {
	@SuppressWarnings("rawtypes")
	public static void getTree(List<Dept> dataList) {
		// 读取层次数据结果集列表
		// List dataList = VirtualDataGenerator.getVirtualResult();
		// 节点列表（映射表，用于临时存储节点对象）
		HashMap<String, DeptVo> nodeList = new HashMap<String, DeptVo>();

		// 根节点
		DeptVo root = null;
		// 将结果集存入映射表（后面将借助映射表构造多叉树）
		for (Dept data : dataList) {
			DeptVo node = new DeptVo();
			node.setCharger1(data.getCharger1());
			node.setCharger2(data.getCharger2());
			node.setDeptCode(data.getDeptCode());
			node.setDeptName(data.getDeptName());
			node.setParentCode(data.getParentCode());
			nodeList.put(node.getDeptCode(), node);
		}
		// 构造无序的多叉树
		Set<Entry<String, DeptVo>> entrySet = nodeList.entrySet();
		for (Iterator<Entry<String, DeptVo>> it = entrySet.iterator(); it.hasNext();) {
			DeptVo node = (DeptVo) it.next().getValue();
			if (node.getParentCode() == null || node.getParentCode().equals("")) {
				root = node;
			} else {
				(nodeList.get(node.getParentCode())).addChild(node);
			}
		}
		// 输出无序的树形结构的JSON字符串
		System.out.println(root);
		
		// 对多叉树进行横向排序
		root.sortChildren();
		// 输出有序的树形结构的JSON字符串
		System.out.println(root);
	}
}
