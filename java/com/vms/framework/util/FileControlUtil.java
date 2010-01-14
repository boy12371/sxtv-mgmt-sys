package com.vms.framework.util;

import java.io.FileInputStream;
import java.io.InputStream;
import com.vms.common.BaseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class FileControlUtil {
	
	public static Document ParseXMLFile(String fileName) throws Exception{
		if(null == fileName || "".equals(fileName)){
			throw new BaseException("","Void xml file name to parse.");
		}
		try{
		DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
		DocumentBuilder dombuilder=domfac.newDocumentBuilder();
		InputStream is=new FileInputStream(fileName);
		Document doc=dombuilder.parse(is);
		is.close();
		return doc;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}
}
