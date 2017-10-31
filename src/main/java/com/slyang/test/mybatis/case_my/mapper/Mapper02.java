package com.slyang.test.mybatis.case_my.mapper;

import com.slyang.test.mybatis.case_my.annotations.Select;

public interface Mapper02 {

	@Select("hello")
	void hello1();

	@Select(value = {"hello", "hello2"})
	int hello2();

	@Select(value = {"hello", "hello2", "hello3"})
	String hello3();

}
