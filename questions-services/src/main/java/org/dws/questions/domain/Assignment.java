package org.dws.questions.domain;

import java.util.List;

public class Assignment {

	public static final int BASIC = 0;
	public static final int ADVANCED = 1;
	
	private long id;
	private int type;
	private String name;
	private List<Student> students;
	private List<Question> questions;
	
	
}
