package com.hatcher.cvte.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hatcher.cvte.utils.DeptCodeComparator;

public class DeptVo implements Serializable {

	private static final long serialVersionUID = 11241234213L;

	private String deptCode;

	private String deptName;

	private String charger1;

	private String charger2;

	private String parentCode;

	private List<DeptVo> children = new ArrayList<DeptVo>();

	// 添加孩子节点
	public void addChild(DeptVo node) {
		children.add(node);
	}

	// 先序遍历，拼接JSON字符串
	public String toString() {
		String result = "{" + "deptCode : '" + deptCode + "'" + ", deptName : '" + deptName + "'";
		if (children.size() != 0) {
			result += ", children : [";
			for (int i = 0; i < children.size(); i++) {
				result += ((DeptVo) children.get(i)).toString() + ",";
			}
			result = result.substring(0, result.length() - 1);
			result += "]";
		} else {
			result += ", leaf : true";
		}
		return result + "}";
	}

	// 兄弟节点横向排序
	@SuppressWarnings("unchecked")
	public void sortChildren() {
		if (children.size() != 0) {
			// 对本层节点进行排序（可根据不同的排序属性，传入不同的比较器，这里 传入ID比较器）
			Collections.sort(children, new DeptCodeComparator());
			// 对每个节点的下一层节点进行排序
			for (int i = 0; i < children.size(); i++) {
				((DeptVo) children.get(i)).sortChildren();
			}
		}
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCharger1() {
		return charger1;
	}

	public void setCharger1(String charger1) {
		this.charger1 = charger1;
	}

	public String getCharger2() {
		return charger2;
	}

	public void setCharger2(String charger2) {
		this.charger2 = charger2;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public int hashCode() {
		String in = deptCode + deptName + parentCode;
		return in.hashCode();

	}

	@Override
	public boolean equals(Object obj) {
		DeptVo s = (DeptVo) obj;
		return deptCode.equals(s.deptCode) && deptName.equals(s.deptName);
	}
}