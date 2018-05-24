package com.bizleap.ds.loader.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.Company;
import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.Employee;
import com.bizleap.ds.loader.AssociationMapper;
import com.bizleap.ds.loader.DataManager;
import com.bizleap.commons.domain.utils.Printer;

@Service("associationMappper")
public class AssociationMapperImpl implements AssociationMapper {
	
	List<Employee> employeeList;
	List<Company> companyList;
	List<Department> departmentList;
	
	@Autowired
	private DataManager dataManager;
	private static Logger logger = Logger.getLogger(AssociationMapperImpl.class);
	private static Printer printer = new Printer(logger);
	 
	/*private void addCompanyToEmployee(Employee employee) {
		
		if(employee==null)
			return;
		
		for(Company company:dataManager.getCompanyList()) {
			if(company.sameBoId(employee.getWorkForCompany())) {
				employee.setWorkForCompany(company);
				return;
			}
		}
		handleLinkageError("Company in employee cannot be linked!",employee,employee.getWorkForCompany());
	}*/
	
	private void addCompanyToDepartment(Department department) {
		
		if(department==null)
			return;
		
		for(Company company:dataManager.getCompanyList()) {
			if(company.sameBoId(department.getWorkForCompany())) {
				department.setWorkForCompany(company);
				return;
			}
		}
		handleLinkageError("Company in department cannot be linked!",department,department.getWorkForCompany());
	}
	
	/*private void addEmployeeToCompany(Company company) {
		//add employeeList to company first
		if(company==null) {
			return;
		}
		
		for(Employee employee:dataManager.getEmployeeList()) {
			if(employee.getWorkForCompany().sameBoId(company)) {
				company.getEmployeeList().add(employee);
				//return;
			}
		}
		//handleLinkageError("Employee in company cannot be linked!", company);
	}*/
	
	private void addDepartmentToCompany(Company company) {
		//add employeeList to company first
		if(company==null) {
			return;
		}
		
		for(Department department:dataManager.getDepartmentList()) {
			if(department.getWorkForCompany().sameBoId(company)) {
				company.getDepartmentList().add(department);
				//return;
			}
		}
		//handleLinkageError("Employee in company cannot be linked!", company);
	}
	
	 
		private void addDepartmentToEmployee(Employee employee) {
			
			if(employee==null)
				return;
			
			for(Department department:dataManager.getDepartmentList()) {
				if(department.sameBoId(employee.getWorkForDepartment())) {
					employee.setWorkForDepartment(department);
					return;
				}
			}
			handleLinkageError("Department in employee cannot be linked!",employee,employee.getWorkForDepartment());
		}
		
		private void addEmployeeToDepartment(Department department) {
		//add employeeList to company first
		if(department==null) {
			return;
		}
		
		for(Employee employee:dataManager.getEmployeeList()) {
			if(employee.getWorkForDepartment().sameBoId(department)) {
				department.getEmployeeList().add(employee);
				//return;
			}
		}
		//handleLinkageError("Employee in company cannot be linked!", company);
	}

	public List<Employee> getEmployeeList() {
		if (employeeList==null)
			this.employeeList=new ArrayList<Employee>();
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Company> getCompanyList() {
		if (companyList==null)
			this.companyList=new ArrayList<Company>();
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
	
	
	
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	private void processEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addDepartmentToEmployee(employee);
		}
	}
	
	private void processCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()) {
			addDepartmentToCompany(company);
		}
	}
	
	private void processDepartmentAssociations() {
		for(Department department:dataManager.getDepartmentList()) {
			addEmployeeToDepartment(department);
			addCompanyToDepartment(department);
		}
	}
	
	public void buildAssociations() {
		processEmployeeAssociations();
		processCompanyAssociations();
		processDepartmentAssociations();
	}
	
	@Override
	public void handleLinkageError(String message, Object object) {
		handleLinkageError(message, object, null);
	}

	@Override
	public void handleLinkageError(String message, Object source, Object issue) {
		reportError(message, source, issue);
		logger.error("", new Exception("Object Linkage Exception"));
		//System.exit(0);
	}

	@Override
	public void reportError(String message, Object source) {
		reportError(message, source, null);
	}

	@Override
	public void reportError(String message, Object source, Object issue) {

		printer.error("Object Link Error", message);

		// if( object != null ) {
		// printer.line( "Source object BoId is: " + ((AbstractEntity)
		// object).getBoId());
		// }

		if (issue != null)
			printer.line2(">>>> Object with the issue is :::: " + issue);

		if (source != null)
			printer.line(">>>> Source object is :::: " + source);

		printer.line("----------------------------------");
	}
}