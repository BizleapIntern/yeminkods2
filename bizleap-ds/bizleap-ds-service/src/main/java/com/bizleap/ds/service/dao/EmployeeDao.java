package com.bizleap.ds.service.dao;

import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeDao extends AbstractDao<Employee, String> {
	public void save(Employee employee) throws ServiceUnavailableException;
}