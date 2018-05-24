package com.bizleap.ds.loader;

import java.io.IOException;

public interface FileLoader {
	public void start(String fileName) throws IOException;
	public void finish();
	public boolean hasNext() throws IOException;
	public String getLine();
	public int getLineNumber();
	public void error(Exception ex);
	public void setLineNumber(int lineNumber);
	public void setLine(String line);
	public void start(String fileName, String encodingType) throws IOException;
	public void addError(int lineNumber, String line);
	public void printErrorMap();
}
