package com.bizleap.commons.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity {

	private String title;
	private int age, salary;
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department workForDepartment;

	public Employee() {
		super();
	}
	
	public Employee(String boId) {
		super(boId);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public Department getWorkForDepartment() {
		return workForDepartment;
	}

	public void setWorkForDepartment(Department workForDepartment) {
		this.workForDepartment = workForDepartment;
	}
	
	public static Employee parseEmployee(String line) {
			String[] tokens=line.split(",");
			Employee employee=new Employee(tokens[0]);
			employee.setName(tokens[1]);
			employee.setAge(Integer.parseInt(tokens[2]));
			employee.setTitle(tokens[3]);
			employee.setSalary(Integer.parseInt(tokens[4]));
			employee.setEmail(tokens[5]);
			employee.setPhone(tokens[6]);
			employee.setWorkForDepartment(new Department(tokens[7]));
			return employee;
	}

	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("title", title)
				.append("age",age)
				.append("salary",salary)
				.append("workForCompany",workForDepartment.getBoId())
				.toString();
	}
}