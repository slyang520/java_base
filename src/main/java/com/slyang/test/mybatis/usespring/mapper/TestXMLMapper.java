package com.slyang.test.mybatis.usespring.mapper;

import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

public interface TestXMLMapper {

	List<Map<String, Object>> test();

	@Insert("INSERT INTO user VALUES ('1234','2345')")
	int test2();

}
