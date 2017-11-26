package com.slyang.test.springaop.springaop.testAop;

import com.slyang.test.springaop.springaop.testAop.child.UserDao2;

public class UserDaoImp2 implements UserDao2 {

	@Override
	public int addUser() {
		System.out.println("UserDao2 add user ......");
		return 6666;
	}

	@Override
	public void updateUser() {
		System.out.println("UserDao2 update user ......");
	}

	@Override
	public void deleteUser() {
		System.out.println("UserDao2 delete user ......");
	}

	@Override
	public void findUser() {
		System.out.println("UserDao2 find user ......");
	}
}
