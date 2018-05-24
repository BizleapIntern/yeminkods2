package com.bizleap.ds.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.enums.EntityType;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.ds.service.EmployeeService;
import com.bizleap.ds.service.dao.EmployeeDao;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends AbstractServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> findByEmployeeBoId(String boId) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.boId=:dataInput";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, boId);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public Employee findByEmployeeBoIdSingle(String boId) throws ServiceUnavailableException {
		List<Employee> employeeList = findByEmployeeBoId(boId);
		if (!CollectionUtils.isEmpty(employeeList)) {
			if (employeeList.size() > 0) {
				return employeeList.get(0);
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public void saveEmployee(Employee employee) throws ServiceUnavailableException {
		if(employee.isBoIdRequired()) {
			employee.setBoId(getNextBoId());
		}
		employeeDao.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employeeList = employeeDao.getAll("From Employee employee");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}
	
	public long getCount() {
		return employeeDao.getCount("select count(emp) from Employee emp");
	}
	
	public String getNextBoId() {
		return getNextBoId(EntityType.EMPLOYEE);
	}
	
	@Override
	public void hibernateInitializeEmployeeList(List<Employee> employeeList) {
		for (Employee employee : employeeList)
			hibernateInitializeEmployee(employee);
	}

	public void hibernateInitializeEmployee(Employee employee) {
		Hibernate.initialize(employee);
	}
}
