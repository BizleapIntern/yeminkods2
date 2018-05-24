package com.bizleap.commons.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "company")
public class Company extends AbstractEntity{
	private String address,ceo;
	
	@OneToMany(mappedBy="workForCompany", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Department> departmentList;
	
	public Company() {
		super();
	}

	public Company(String boId) {
		super(boId);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	
	public List<Department> getDepartmentList() {
		if (departmentList==null)
			this.departmentList=new ArrayList<Department>();
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	
	public static Company parseCompany(String line) {
		String[] tokens=line.split(",");
		Company company=new Company(tokens[0]);
		company.setName(tokens[1]);
		company.setAddress(tokens[2]);
		company.setPhone(tokens[3]);
		company.setEmail(tokens[4]);
		company.setCeo(tokens[5]);
		return company;
	}
	
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("address", address)
				.append("ceo",ceo)
				.toString();
	}	
}