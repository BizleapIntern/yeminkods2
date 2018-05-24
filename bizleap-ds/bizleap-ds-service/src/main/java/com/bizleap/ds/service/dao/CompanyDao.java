package com.bizleap.ds.service.dao;

import com.bizleap.commons.domain.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface CompanyDao extends AbstractDao<Company, String> {
	public void save(Company company) throws ServiceUnavailableException;
}