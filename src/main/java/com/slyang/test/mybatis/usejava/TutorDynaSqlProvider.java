package com.slyang.test.mybatis.usejava;

import org.apache.ibatis.jdbc.SQL;

public class TutorDynaSqlProvider {

//	public static void main(String[] args) {
//		TutorDynaSqlProvider tutorDynaSqlProvider = new TutorDynaSqlProvider();
//		tutorDynaSqlProvider.findTutorByIdSql(123456);
//
//	}

	public String findTutorByIdSql(final int tutorId) {
		return new SQL() {{
			SELECT("tutor_id as tutorId, name, email");
			FROM("tutors");
			WHERE("tutor_id=" + tutorId);
		}}.toString();
	}

}