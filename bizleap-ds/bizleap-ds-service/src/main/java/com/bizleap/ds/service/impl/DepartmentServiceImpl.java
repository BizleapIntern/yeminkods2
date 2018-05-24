package com.bizleap.ds.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.enums.EntityType;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.ds.service.DepartmentService;
import com.bizleap.ds.service.EmployeeService;
import com.bizleap.ds.service.dao.DepartmentDao;

@Service("departmentService")
@Transactional(readOnly = true)
public class DepartmentServiceImpl extends AbstractServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public List<Department> findByDepartmentBoId(String boId) throws ServiceUnavailableException {
		String queryStr = "select department from Department department where department.boId=:dataInput";
		List<Department> departmentList;
		departmentList = departmentDao.findByString(queryStr, boId);
		hibernateInitializeDepartmentList(departmentList);
		return departmentList;
	}

	@Override
	public Department findByDepartmentBoIdSingle(String boId) throws ServiceUnavailableException {
		List<Department> departmentList = findByDepartmentBoId(boId);
		if (!CollectionUtils.isEmpty(departmentList)) {
			if (departmentList.size() > 0) {
				return departmentList.get(0);
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public void saveDepartment(Department department) throws ServiceUnavailableException {
		if(department.isBoIdRequired()) {
			department.setBoId(getNextBoId());
		}
		departmentDao.save(department);
	}

	@Override
	public List<Department> getAllDepartment() throws ServiceUnavailableException {
		List<Department> departmentList = departmentDao.getAll("From Department department");
		hibernateInitializeDepartmentList(departmentList);
		return departmentList;
	}
	
	public long getCount() {
		return departmentDao.getCount("select count(dept) from Department dept");
	}
	
	public String getNextBoId() {
		return getNextBoId(EntityType.DEPARTMENT);
	}
	
	@Override
	public void hibernateInitializeDepartmentList(List<Department> departmentList) throws ServiceUnavailableException {
		for (Department department : departmentList)
			hibernateInitializeDepartment(department);
	}

	public void hibernateInitializeDepartment(Department department) throws ServiceUnavailableException {
		employeeService.hibernateInitializeEmployeeList(department.getEmployeeList());
	}
}
