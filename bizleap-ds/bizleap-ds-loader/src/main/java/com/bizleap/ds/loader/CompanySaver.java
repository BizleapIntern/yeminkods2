package com.bizleap.ds.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface CompanySaver {
	
	public void savePass1() throws ServiceUnavailableException, IOException;
	public void setCompanyList(List<Company> companyList);
}
