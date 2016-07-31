package com.hatcher.cvte.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hatcher.cvte.model.UserDept;

public interface UserDeptMapper {

	UserDept selectByPrimaryKey(String id);
	
	List<String> selectByUserCode(@Param("userCode") String userCode);

}