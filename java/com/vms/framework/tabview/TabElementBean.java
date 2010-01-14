package com.vms.framework.tabview;

import java.util.List;

public class TabElementBean {

	private String name;

	private String URL;
	
	private List<TabElementBean> subTabs;

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the URL.
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param name
	 *            The URL to set.
	 */
	public void setURL(String URL) {
		this.URL = URL;
	}

	public void setSubTabs(List<TabElementBean> subTabs) {
		this.subTabs = subTabs;
	}

	public List<TabElementBean> getSubTabs() {
		return subTabs;
	}

}
