package com.bizleap.ds.service;

import java.util.List;

import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface DepartmentService {
	public List<Department> findByDepartmentBoId(String boId)throws ServiceUnavailableException;
	public Department findByDepartmentBoIdSingle(String boId)throws ServiceUnavailableException;
	public void saveDepartment(Department department)throws ServiceUnavailableException;
	public List<Department> getAllDepartment()throws ServiceUnavailableException;
	public void hibernateInitializeDepartmentList(List<Department> departmentList)throws ServiceUnavailableException;
}
