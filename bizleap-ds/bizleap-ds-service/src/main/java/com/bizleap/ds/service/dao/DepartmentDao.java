package com.bizleap.ds.service.dao;

import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface DepartmentDao extends AbstractDao<Department, String> {
	public void save(Department department) throws ServiceUnavailableException;
}
