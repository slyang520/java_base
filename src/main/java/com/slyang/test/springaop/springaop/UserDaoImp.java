package com.slyang.test.springaop.springaop;

public class UserDaoImp implements UserDao {
	@Override
	public int addUser() {
		System.out.println("add user ......");
		return 6666;
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
