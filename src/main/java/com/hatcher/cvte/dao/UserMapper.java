package com.hatcher.cvte.dao;

import java.util.List;

import com.hatcher.cvte.model.User;

public interface UserMapper {
	List<User> selectByKey(User user);

	User selectByUserCode(String userCode);
}
