package com.slyang.test.mybatis.usespring.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestMapper {

	@Select("SELECT * FROM xgobbs_user")
	List<Map<String, Object>> test();

	@Insert("INSERT INTO user VALUES ('1234','2345')")
	int test2();

}
