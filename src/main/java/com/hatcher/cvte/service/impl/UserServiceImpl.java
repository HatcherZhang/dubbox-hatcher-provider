package com.hatcher.cvte.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatcher.cvte.dao.DeptMapper;
import com.hatcher.cvte.dao.MenuMapper;
import com.hatcher.cvte.dao.UserDeptMapper;
import com.hatcher.cvte.dao.UserMapper;
import com.hatcher.cvte.dao.UserMenuMapper;
import com.hatcher.cvte.model.Dept;
import com.hatcher.cvte.model.Menu;
import com.hatcher.cvte.model.User;
import com.hatcher.cvte.service.UserService;
import com.hatcher.cvte.utils.TreeUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserMenuMapper userMenuMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public User islogin(User user) {
		List<User> list = userMapper.selectByKey(user);
		if (list.size() == 1) {
			return list.get(0);
		} else {
			return new User();
		}

	}

	@Override
	public List<Menu> getUserMenus(String userCode) {
		// List<String> userMenuCodes = new ArrayList<String>();
		// ArrayList<Menu> result = new ArrayList<Menu>();
		// userMenuCodes = userMenuMapper.selectByUserCode(userCode);
		// for (String menuCode : userMenuCodes) {
		// getMenusByRank(result, menuCode);
		// }
		// return result;

		List<String> userMenuCodes = new ArrayList<String>();
		ArrayList<Menu> result = new ArrayList<Menu>();
		userMenuCodes = userMenuMapper.selectByUserCode(userCode);
		for (String menuCode : userMenuCodes) {
			result.addAll(menuMapper.getMenuChildList(menuCode));
			result.addAll(menuMapper.getMenuParentList(menuCode));
		}
		// 去重
		List<Menu> listWithoutDup = new ArrayList<>(new HashSet<>(result));
		return listWithoutDup;
	}

	@Override
	public List<Dept> getUserDepts(String userCode) {

		List<String> deptMenuCodes = new ArrayList<String>();
		ArrayList<Dept> result = new ArrayList<Dept>();
		// 单独授权的部门
		deptMenuCodes = userDeptMapper.selectByUserCode(userCode);
		// 用户自身所属部门
		User user = userMapper.selectByUserCode(userCode);
		deptMenuCodes.add(user.getDeptCode());
		// 部门负责人
		deptMenuCodes.addAll(deptMapper.selectByUserCode(userCode));

		for (String deptCode : deptMenuCodes) {
			result.addAll(deptMapper.getDeptChildList(deptCode));
			// for (Dept demo : deptMapper.getDeptChildList(deptCode)) {
			// System.out.println("Child:" + demo.getDeptCode());
			// }
			// System.out.println("");

			result.addAll(deptMapper.getDeptParentList(deptCode));
			// for (Dept demo : deptMapper.getDeptParentList(deptCode)) {
			// System.out.println("Parent:" + demo.getDeptCode());
			// }
			// System.out.println("");
		}
		// 去重
		List<Dept> listWithoutDup = new ArrayList<>(new HashSet<>(result));
		TreeUtils.getTree(listWithoutDup);
		return listWithoutDup;
	}

	/**
	 * 递归查询父节点
	 * 
	 * @param result
	 * @param menuCode
	 * @return
	 */
	public ArrayList<Menu> getMenusByRank(ArrayList<Menu> result, String menuCode) {
		Menu menu = menuMapper.selectByPrimaryKey(menuCode);
		if (result.contains(menu)) {
		} else {
			result.add(menu);
		}
		System.out.println(menu.getMenuName());
		if (menu.getParentMenuCode() != null) {
			getMenusByRank(result, menu.getParentMenuCode());
		}
		return result;
	}
}
