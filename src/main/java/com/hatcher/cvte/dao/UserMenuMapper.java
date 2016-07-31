package com.hatcher.cvte.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hatcher.cvte.model.UserMenu;

public interface UserMenuMapper {

	UserMenu selectByPrimaryKey(String id);

	List<String> selectByUserCode(@Param("userCode") String userCode);
}