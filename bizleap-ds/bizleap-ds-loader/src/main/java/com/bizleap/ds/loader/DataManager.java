package com.bizleap.ds.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.Company;
import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface DataManager {
	public void load() throws IOException, ServiceUnavailableException;
	public void print();
	public List<Employee> getEmployeeList();
	public List<Company> getCompanyList();
	public List<Department> getDepartmentList();
}
