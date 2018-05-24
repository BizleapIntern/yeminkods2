package com.bizleap.commons.domain.utils;

import org.apache.log4j.Logger;
import org.apache.commons.lang3.StringUtils;

public class Printer {

	private Logger logger;

	public Printer(Logger logger) {
		this.logger = logger;
	}

	public void line(String value) {
		logger.debug(value);
	}

	public void line2(String value) {
		logger.debug("");
		logger.debug(value);
		logger.debug("");
	}

	public void header(String value) {
		header("", value);
	}

	public void header(String title, Object value) {
		logger.debug("");
		if (StringUtils.isNotEmpty(title))
			logger.debug("----------- " + title + " ------------");
		else
			logger.debug("-----------------------------------------------");
		logger.debug("   " + value);
		logger.debug("-----------------------------------------------");
	}
	
	public void error( String title, Object value ) { 
		logger.debug("");
		if( StringUtils.isNotEmpty( title) ) 
			logger.debug(">>>>>>>>>>>>>> ERROR: " + title +  " <<<<<<<<<<<<<");
		else logger.debug("-----------------------------------------------");
		logger.debug("   " + value );
		logger.debug("-----------------------------------------------");
	}
	
	public void footer( String value ) { 
		
		String value2 = value; 
		
		if( StringUtils.isBlank(value))
			value2 = "done"; 
		logger.debug("------- " + value2 + " -------");
		logger.debug("");
	}

}