package com.slyang.test.springaop.springaop.testAop;

import com.slyang.test.springaop.springaop.testAop.UserDao;

public class UserDaoImp implements UserDao {
	@Override
	public int addUser(String params1) {
		System.out.println("add user ......");
		//testInner();
		return 6666;
	}

	public int testInner(){
		System.out.println("testInner ......");
		return 1;
	}


	@Override
	public void updateUser() {
		System.out.println("update user ......");
	}

	@Override
	public void deleteUser() {
		System.out.println("delete user ......");
	}

	@Override
	public void findUser() {
		System.out.println("find user ......");
	}
}
