package com.hatcher.cvte.utils;

import java.util.Comparator;

import com.hatcher.cvte.model.vo.DeptVo;

@SuppressWarnings("rawtypes")
public class DeptCodeComparator implements Comparator {
	// 按照节点编号比较
	public int compare(Object o1, Object o2) {
		int j1 = Integer.parseInt(((DeptVo) o1).getDeptCode());
		int j2 = Integer.parseInt(((DeptVo) o2).getDeptCode());
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
	}
}