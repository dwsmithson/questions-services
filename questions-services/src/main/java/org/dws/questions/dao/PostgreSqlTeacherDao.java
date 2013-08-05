package org.dws.questions.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.dws.questions.domain.Teacher;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PostgreSqlTeacherDao extends AbstractPostgreSqlDao<Teacher> implements TeacherDao {
	
	private static final String ID = "id";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME =  "last_name";
	private static final String EMAIL = "email";

	private static final String CREATE_SQL = 
			"insert into teacher (first_name, last_name, email) "
			+ "values (:firstName, :lastName, :email)";
	
	private static final String FIND_ALL_SQL =
			"select id, first_name, last_name, email from teacher";
	
	private static final String FIND_BY_ID = 
			"select id, first_name, last_name, email from teacher where id = :id";
	
	private static final String UPDATE_SQL = 
			"update teacher set (first_name, last_name, email) = (:firstName, :lastName, :email) where id = :id";
	
	private static final String DELETE_BY_ID =
			"delete from teacher where id = :id";
	
	private static final String DELETE_ALL_SQL = 
			"delete from teacher";
	
	private static final String COUNT_SQL = 
			"select count(*) from teacher";
	
	@Inject NamedParameterJdbcOperations jdbcTemplate;
	@Inject TeacherRowMapper teacherRowMapper;
	
	@Override
	public void create(Teacher teacher) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue(FIRST_NAME, teacher.getFirstName())
			.addValue(LAST_NAME, teacher.getLastName())
			.addValue(EMAIL, teacher.getEmail());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(CREATE_SQL, params, keyHolder);
		teacher.setId(keyHolder.getKey().longValue());
		
	}

	@Override
	public Teacher get(Serializable id) {
		
		SqlParameterSource params = new MapSqlParameterSource(ID, id);
		return jdbcTemplate.queryForObject(FIND_BY_ID, params, teacherRowMapper);
	}

	@Override
	public List<Teacher> getAll() {
		return jdbcTemplate.query(FIND_ALL_SQL, new HashMap<String, Object>(), teacherRowMapper);
	}

	@Override
	public void update(Teacher teacher) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue(ID, teacher.getId())
			.addValue(FIRST_NAME, teacher.getFirstName())
			.addValue(LAST_NAME, teacher.getLastName())
			.addValue(EMAIL, teacher.getEmail());
		jdbcTemplate.update(UPDATE_SQL, params);
		
	}

	@Override
	public void delete(Teacher teacher) {
		deleteById(teacher.getId());
	}

	@Override
	public void deleteById(Serializable id) {
		SqlParameterSource params = new MapSqlParameterSource(ID, id);
		jdbcTemplate.update(DELETE_BY_ID, params);
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(DELETE_ALL_SQL, new HashMap<String, Object>());
		
	}

	@Override
	public long count() {
		Integer count = jdbcTemplate.queryForObject(COUNT_SQL, new HashMap<String, Object>(), Integer.class);
		return count.intValue();
	}

	@Override
	public boolean exists(Serializable id) {
		Teacher teacher = get(id);
		if (teacher != null) {
			return true;
		}
		return false;
	}
	
	
}
