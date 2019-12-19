package com.slyang.test.mybatis.usexml.noxml;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestMapperNoXml {

	@Select("select 1")
	List<Map<String, Object>> test();

}
