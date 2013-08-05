package org.dws.questions.domain;

public class Teacher {

	private long id;
	private String firstName;
	private String lastName;
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Teacher-->id:");
		sb.append(id);
		sb.append("|firstName:");
		sb.append(firstName);
		sb.append("|lastName:");
		sb.append(lastName);
		sb.append("|email:");
		sb.append(email);
		sb.append("\n");
		return sb.toString();
	}
	
}
