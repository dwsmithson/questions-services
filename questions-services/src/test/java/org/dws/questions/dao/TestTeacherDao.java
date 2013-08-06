package org.dws.questions.dao;

import java.util.List;

import javax.inject.Inject;

import org.dws.questions.domain.Teacher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class TestTeacherDao {

	EmbeddedDatabase database = null;
	
	private TeacherDao getDao() throws Exception {
		PostgreSqlTeacherDao teacherDao = new PostgreSqlTeacherDao();
		teacherDao.jdbcTemplate = new NamedParameterJdbcTemplate(database);
		teacherDao.teacherRowMapper = new TeacherRowMapper();
		return teacherDao;
	}
	
	@Before
	public void setUp() {
		database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("testdb22")
			.addScript("classpath:org/dws/questions/dao/questions-test-schema-postgresql.sql")
			.build();
		Assert.assertEquals("database creation successful", true, database != null);
	}
	
	@After
	public void tearDown() {
		database.shutdown();
	}
	
	@Test
	public void testGetAll() throws Exception {
		
		System.out.println("Executing testGetAll ...");
		
		TeacherDao teacherDao = getDao();
		
		List<Teacher> teachers = teacherDao.getAll();
		Teacher teacher = teachers.get(0);
		Assert.assertEquals("id", 1L, teacher.getId());
		Assert.assertEquals("firstName", "George", teacher.getFirstName());
		Assert.assertEquals("lastName", "Washington", teacher.getLastName());
		Assert.assertEquals("email", "george.washington@gmail.com", teacher.getEmail());
		
        teacher = teachers.get(1);
		Assert.assertEquals("firstName", "Abraham", teacher.getFirstName());
		Assert.assertEquals("lastName", "Lincoln", teacher.getLastName());
		Assert.assertEquals("email", "abraham.lincoln@gmail.com", teacher.getEmail());
		
        teacher = teachers.get(2);
		Assert.assertEquals("firstName", "John", teacher.getFirstName());
		Assert.assertEquals("lastName", "Kennedy", teacher.getLastName());
		Assert.assertEquals("email", "john.kennedy@gmail.com", teacher.getEmail());
	
	}
	
	@Test
	public void testGet() throws Exception {
		System.out.println("Executing testGet ...");
		
		TeacherDao teacherDao = getDao();
		
		Teacher teacher = teacherDao.get(1L);
		Assert.assertEquals("id", 1L, teacher.getId());
		Assert.assertEquals("firstName", "George", teacher.getFirstName());
		Assert.assertEquals("lastName", "Washington", teacher.getLastName());
		Assert.assertEquals("email", "george.washington@gmail.com", teacher.getEmail());
	}
	
	@Test
	public void testCount() throws Exception {
		System.out.println("Executing testCount ...");
		
		TeacherDao teacherDao = getDao();
		
		long count = teacherDao.count();
		Assert.assertEquals("count", 3L, count);
		
	}
	
	@Test
	public void testExists() throws Exception {
		System.out.println("Executing testExists ...");

		TeacherDao teacherDao = getDao();
		
		Assert.assertEquals("exists", true, teacherDao.exists(1L));
		Assert.assertEquals("exists", true, teacherDao.exists(2L));
		Assert.assertEquals("exists", true, teacherDao.exists(3L));
		Assert.assertEquals("not exists", true, !teacherDao.exists(99L));
	}
	
	@Test
	public void testCreate() throws Exception {
		
		TeacherDao teacherDao = getDao();
		
		Teacher teacher = new Teacher();
		teacher.setFirstName("Woodrow");
		teacher.setLastName("Wilson");
		teacher.setEmail("woodrow.wilson@gmail.com");
		teacherDao.create(teacher);
		Assert.assertEquals("created id", 4L, teacher.getId());
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		
		TeacherDao teacherDao = getDao();
		
		teacherDao.deleteAll();
		Assert.assertEquals("delete all count",  0L, teacherDao.count());
	}
	
	@Test
	public void testDeleteById() throws Exception {

		TeacherDao teacherDao = getDao();
		
		teacherDao.deleteById(1L);
		Assert.assertEquals("not exists", true, !teacherDao.exists(1L));
		Assert.assertEquals("count", 2L, teacherDao.count());
		
	}
	
	@Test
	public void testDelete() throws Exception {
		
		TeacherDao teacherDao = getDao();
		Teacher teacher = new Teacher();
		teacher.setId(2L);
		
		teacherDao.delete(teacher);
		Assert.assertEquals("not exists", true, !teacherDao.exists(2L));
		Assert.assertEquals("count", 2L, teacherDao.count());
	}
	
}


