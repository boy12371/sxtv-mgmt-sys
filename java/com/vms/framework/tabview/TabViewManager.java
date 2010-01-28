package com.vms.framework.tabview;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vms.common.BaseException;
import com.vms.framework.util.FileControlUtil;

public class TabViewManager {
	private List<TabElementBean> tabs = null;

	private TabViewManager(){}
	
	public static TabViewManager getInstance(HttpSession session) throws Exception{
		TabViewManager instance = (TabViewManager)session.getAttribute("TabManager");
		if(null == instance){
			instance = new TabViewManager();
			String path = session.getServletContext().getRealPath("/");
			String filePath = path+"conf/frame_base.xml";
			instance.initializeTabs(filePath);
			session.setAttribute("TabManager", instance);
		}
		return instance;
	}
	
	private void initializeTabs(String filePath) throws Exception{
		tabs = new ArrayList<TabElementBean>();
		NodeList mainConsoleConfNode = null;
		Document doc = FileControlUtil.ParseXMLFile(filePath);
		mainConsoleConfNode = doc.getElementsByTagName("MainConsole");
		
		int numItems = mainConsoleConfNode.getLength();
		if (numItems == 0){
			throw new BaseException("","No tab configed.");
		}
		Node node = mainConsoleConfNode.item(0);
		NodeList modules = node.getChildNodes();
		//attribute for each module
		
		if (modules != null) {	
			int modulesLength = modules.getLength();
			for(int i = 0; i < modulesLength; i++){
				//if the child node isn't layout, don't used
				Node tempModule = modules.item(i);
				if (!tempModule.getNodeName().equals("Module")) {
					continue;
				}
				
				TabElementBean mainTab = new TabElementBean();
				
				NamedNodeMap attributesModule = tempModule.getAttributes();
				String tabName = attributesModule.getNamedItem("name").getNodeValue();
				mainTab.setName(tabName);
				String tabURL = attributesModule.getNamedItem("url").getNodeValue();
				mainTab.setUrl(tabURL);
				
				//get the its all child info
				//if the child node isn't layout, don't used
				NodeList moduleItems = tempModule.getChildNodes();
				if(moduleItems != null){
					int modulesItemLength = moduleItems.getLength();
					if(modulesItemLength != 0){
						List<TabElementBean> subTabs = new ArrayList<TabElementBean>();
						for(int j = 0; j < modulesItemLength; j++){
							Node tempModuleItem = moduleItems.item(j);
							TabElementBean subTab = new TabElementBean();
							if (!tempModuleItem.getNodeName().equals("ModuleItem")) {
								continue;
							}
							NamedNodeMap attributesModuleItem = tempModuleItem.getAttributes();						
							String subTabName = attributesModuleItem.getNamedItem("name").getNodeValue();
							subTab.setName(subTabName);
							String subTabURL = attributesModuleItem.getNamedItem("url").getNodeValue();
							subTab.setUrl(subTabURL);
							
							subTabs.add(subTab);
						}
						mainTab.setSubTabs(subTabs);
					}
				}
				
				//save a module all info
				tabs.add(mainTab);
			}
		}
	}
	
	public List<TabElementBean> getTabs() {
		return tabs;
	}
}
