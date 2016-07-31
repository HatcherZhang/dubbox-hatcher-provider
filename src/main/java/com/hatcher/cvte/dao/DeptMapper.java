package com.hatcher.cvte.dao;

import java.util.List;

import com.hatcher.cvte.model.Dept;

public interface DeptMapper {

	List<Dept> getDeptChildList(String deptCode);

	List<Dept> getDeptParentList(String deptCode);
	
	List<String> selectByUserCode(String userCode);
	
    Dept selectByPrimaryKey(String deptCode);

}