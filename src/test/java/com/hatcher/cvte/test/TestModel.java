package com.hatcher.cvte.test;

import com.hatcher.cvte.model.User;

public class TestModel {

	public static void main(String[] args) {
		User user = new User();
		System.out.println(user.getUserCode());
		if(user.getUserCode()==null){
			System.out.println("asda");
		}
	}

}
