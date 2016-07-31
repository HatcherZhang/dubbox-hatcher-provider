package com.hatcher.cvte.dao;

import java.util.List;

import com.hatcher.cvte.model.Menu;

public interface MenuMapper {

	Menu selectByPrimaryKey(String menuCode);

	List<Menu> getMenuChildList(String menuCode);

	List<Menu> getMenuParentList(String menuCode);

	List<Menu> selectAllMenu();
}