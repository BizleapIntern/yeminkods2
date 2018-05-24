package com.bizleap.ds.standalone.impl.test;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizleap.ds.loader.DataManager;
import com.bizleap.ds.service.CompanyService;
import com.bizleap.ds.service.DepartmentService;
import com.bizleap.ds.service.EmployeeService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml","classpath:/hibernateContext.xml"})
@PropertySource({"classpath:log4j.properties", "classpath:/application.properties"})
@ComponentScan({"com.bizleap.training.ds.loader"})
public class ServiceTest {
	
	@Autowired
	@Qualifier("dataManager")
	protected DataManager dataManager;
	
	//@Autowired
	//@Qualifier("userService")	
	//protected UserService userService;

	//@Autowired
	//public DataService dataService;
	
	@Autowired
	protected CompanyService companyService;
	
	@Autowired
	protected EmployeeService employeeService;	
	
	@Autowired
	protected DepartmentService departmentService;	
	
	//SearchRequest request = new SearchRequest();
	
	/*private static Logger logger = Logger.getLogger(ServiceTest.class);
	
	protected LoginProfile getLoginProfile(String userName, String password) {
		request.setKey(SearchKey.LOGIN);
		request.setUserName("unyuntthan");
		request.setPassword("punyuntthan");
		request.setEntityType(EntityType.LOGIN_USER);
		try {
			return (LoginProfile) userService.searchUser(request).getEntityList().get(0);
		} catch (ServiceUnavailableException e) {
			logger.error("Can't log in ",e);
			return null;
		}
	}*/
}