package org.dws.questions.dao;

import java.util.List;

import javax.inject.Inject;

import org.dws.questions.domain.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestTeacherDao {
	
	@Inject public PostgreSqlTeacherDao teacherDao;

	@Test
	public void testGetAll() throws Exception {
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
}
