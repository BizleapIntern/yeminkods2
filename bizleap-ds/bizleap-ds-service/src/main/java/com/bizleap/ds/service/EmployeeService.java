package com.bizleap.ds.service;

import java.util.List;

import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeService {
	public List<Employee> findByEmployeeBoId(String boId)throws ServiceUnavailableException;
	public Employee findByEmployeeBoIdSingle(String boId)throws ServiceUnavailableException;
	public void saveEmployee(Employee employee)throws ServiceUnavailableException;
	public List<Employee> getAllEmployee()throws ServiceUnavailableException;
	public void hibernateInitializeEmployeeList(List<Employee>employees)throws ServiceUnavailableException;
}
