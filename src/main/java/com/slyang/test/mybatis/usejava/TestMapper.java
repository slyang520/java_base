package com.slyang.test.mybatis.usejava;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

interface TestMapper {

	@Select("SELECT * FROM xgobbs_user")
	List<Map<String, Object>> test();

}
