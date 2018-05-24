package com.bizleap.ds.standalone.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public class DepartmentServiceImplTest extends ServiceTest{
	
	static Logger logger=Logger.getLogger(DepartmentServiceImplTest.class);
	
	@Ignore
	@Test
	public void testFindByDepartmentBoId() throws ServiceUnavailableException {
		assertNotNull(departmentService.findByDepartmentBoId("DEPT01"));
		assertTrue(CollectionUtils.isNotEmpty(departmentService.findByDepartmentBoId("DEPT01")));
		assertEquals("DEPT01",departmentService.findByDepartmentBoIdSingle("DEPT01").getBoId());
	}
	
	@Ignore
	@Test
	public void testGetAllDepartment() throws ServiceUnavailableException {
		assertNotNull(departmentService.getAllDepartment());
		assertTrue(CollectionUtils.isNotEmpty(departmentService.getAllDepartment()));
		assertEquals(6, departmentService.getAllDepartment().size());
	}
}
