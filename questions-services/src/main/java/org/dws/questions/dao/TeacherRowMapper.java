package org.dws.questions.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dws.questions.domain.Teacher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TeacherRowMapper implements RowMapper<Teacher> {

	public Teacher mapRow(ResultSet resultSet, int rowNum)
	throws SQLException {
		
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getLong("ID"));
		teacher.setFirstName(resultSet.getString("FIRST_NAME"));
		teacher.setLastName(resultSet.getString("LAST_NAME"));
		teacher.setEmail(resultSet.getString("EMAIL"));
		
		return teacher;
	}
}
