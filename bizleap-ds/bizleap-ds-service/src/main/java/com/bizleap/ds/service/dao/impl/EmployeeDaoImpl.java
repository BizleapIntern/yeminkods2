package com.bizleap.ds.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.ds.service.dao.EmployeeDao;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<Employee, String> implements EmployeeDao {

	protected EmployeeDaoImpl() {
		super(Employee.class);
	}

	@Override
	public void save(Employee employee) throws ServiceUnavailableException {
		saveOrUpdate(employee);
	}
}