package com.bizleap.ds.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.ds.service.dao.DepartmentDao;

@Repository
public class DepartmentDaoImpl extends AbstractDaoImpl<Department, String> implements DepartmentDao {

	protected DepartmentDaoImpl() {
		super(Department.class);
	}

	@Override
	public void save(Department department) throws ServiceUnavailableException {
		saveOrUpdate(department);
	}
}