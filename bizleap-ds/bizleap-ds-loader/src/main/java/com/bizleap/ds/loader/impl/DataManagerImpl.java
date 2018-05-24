package com.bizleap.ds.loader.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.Company;
import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.ds.loader.AssociationMapper;
import com.bizleap.ds.loader.CompanySaver;
import com.bizleap.ds.loader.DataLoader;
import com.bizleap.ds.loader.DataManager;

@Service("dataManager")
public class DataManagerImpl implements DataManager {
	List<Employee> employeeList;
	List<Company> companyList;
	List<Department> departmentList;
	
	@Autowired
	private DataLoader dataLoader;
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private CompanySaver companySaver;
	
	public void load() throws IOException, ServiceUnavailableException {
		//load the data
		dataLoader.loadData();
		//build the association
		associationMapper.buildAssociations();
		
		companySaver.setCompanyList(companyList);
		companySaver.savePass1();
	}

	public void print() {
		for(Employee employee:employeeList) {
			System.out.println(employee);
		}
		
		for(Company company:companyList) {
			System.out.println(company);
		}
		
		for(Department department:departmentList) {
			System.out.println(department);
		}
	}

	@Override
	public List<Employee> getEmployeeList() {
		if (employeeList==null)
			this.employeeList=new ArrayList<Employee>();
		return employeeList;
	}

	@Override
	public List<Company> getCompanyList() {
		if (companyList==null)
			this.companyList=new ArrayList<Company>();
		return companyList;
	}
	
	@Override
	public List<Department> getDepartmentList() {
		if (departmentList==null)
			this.departmentList=new ArrayList<Department>();
		return departmentList;
	}
}