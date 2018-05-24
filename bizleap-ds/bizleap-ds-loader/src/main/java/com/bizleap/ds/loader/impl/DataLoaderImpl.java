package com.bizleap.ds.loader.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.Company;
import com.bizleap.commons.domain.Department;
import com.bizleap.commons.domain.Employee;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.ds.loader.DataLoader;
import com.bizleap.ds.loader.DataManager;

@Service("dataLoader")
public class DataLoaderImpl implements DataLoader {
	
	static Logger logger=Logger.getLogger(DataLoaderImpl.class);
	private static Printer printer = new Printer( logger ); 
	
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	private FileLoaderImpl fileLoaderImpl;
	
	public void loadEmployee(String fileName) {
		try {
			fileLoaderImpl.start(fileName);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		Employee employee;
		
		try {
			while(fileLoaderImpl.hasNext()) {
				try {
					employee=Employee.parseEmployee(fileLoaderImpl.getLine());
					if(employee!=null) {
						dataManager.getEmployeeList().add(employee);
					}
				}catch(Exception e){
					fileLoaderImpl.error(e);
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		fileLoaderImpl.finish();
	}
	
	public void loadDepartment(String fileName) {
		try {
			fileLoaderImpl.start(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Department department;
		
		try {
			while(fileLoaderImpl.hasNext()) {
				try {
					department=	Department.parseDepartment(fileLoaderImpl.getLine());
					if(department!=null) {
						dataManager.getDepartmentList().add(department);
					}
				} catch(Exception e){
					fileLoaderImpl.error(e);
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		fileLoaderImpl.finish();
	}

	public void loadCompany(String fileName) {
		try {
			fileLoaderImpl.start(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Company company;
		
		try {
			while(fileLoaderImpl.hasNext()) {
				try {
					company=Company.parseCompany(fileLoaderImpl.getLine());
					if(company!=null) {
						dataManager.getCompanyList().add(company);
					}
				} catch(Exception e){
					fileLoaderImpl.error(e);
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		fileLoaderImpl.finish();
	}

	@Override
	public void loadData() {
		printer.line("Data Load Started....");
		
		loadEmployee("employee.txt");
		printer.line("Employee Loaded : "+dataManager.getEmployeeList().size());
		
		loadCompany("company.txt");
		printer.line("Company Loaded : "+dataManager.getCompanyList().size());
		
		loadDepartment("department.txt");
		printer.line("Department Loaded : "+dataManager.getDepartmentList().size());
		
		printer.line("Data Load Completed....");
	}
}