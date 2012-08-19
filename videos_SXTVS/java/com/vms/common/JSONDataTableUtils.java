package com.vms.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vms.beans.JSONDataTable;

public class JSONDataTableUtils {
	
	public final static String SORT_DIRECTION="asc";
	
	public static JSONDataTable initJSONDataTable(HttpServletRequest request){
		
		String sort = request.getParameter("sort");
		String dir = request.getParameter("dir");
		int startIndex,results;
		
		String temp = request.getParameter("startIndex");
		if(null != temp && !"".equals(temp)){
			startIndex = Integer.parseInt(temp);
		}else{
			startIndex = 0;
		}
		temp = request.getParameter("results");
		if(null != temp && !"".equals(temp)){
			results = Integer.parseInt(temp);
		}else{
			results = 0;
		}
		
		JSONDataTable  dataTable = new JSONDataTable();
		dataTable.setDir(dir);
		dataTable.setRowsPerPage(results);
		dataTable.setSort(sort);
		dataTable.setStartIndex(startIndex);
		return dataTable;
	}
	
	public static void setupJSONDataTable(List data, JSONDataTable dataTable, int totalSize){
		dataTable.setRecords(data);
		dataTable.setRecordsReturned(data.size());
		dataTable.setTotalRecords(totalSize);
	}

}
