package com.bizleap.ds.standalone.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.ds.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest extends ServiceTest{

	static Logger logger=Logger.getLogger(EmployeeServiceImplTest.class);
	
		@Ignore
		@Test
		public void testFindByEmployeeBoId() throws ServiceUnavailableException {
			assertNotNull(employeeService.findByEmployeeBoId("PER01"));
			assertTrue(CollectionUtils.isNotEmpty(employeeService.findByEmployeeBoId("PER01")));
			assertEquals("PER01",employeeService.findByEmployeeBoIdSingle("PER01").getBoId());
		}
		
		@Ignore
		@Test
		public void testGetAllEmployee() throws ServiceUnavailableException {
			assertNotNull(employeeService.getAllEmployee());
			assertTrue(CollectionUtils.isNotEmpty(employeeService.getAllEmployee()));
			assertEquals(8, employeeService.getAllEmployee().size());
		}
}
