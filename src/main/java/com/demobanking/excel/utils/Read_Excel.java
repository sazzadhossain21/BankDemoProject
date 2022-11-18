package com.demobanking.excel.utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Read_Excel {
	
	@DataProvider(name="loginTest")
	public String[][] setData() throws IOException {
		
		String path = System.getProperty("user.dir")+"/src/test/resources/TestData/testData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Credential");
		int colnum = XLUtils.getCellCount(path, "Credential", 0);
		String [][] data = new String[rownum][colnum];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colnum;j++) {
				
				data[i-1][j]=XLUtils.getCellData(path, "Credential", i, j);
			}
		}
		return data;
	}	
	
	
}
