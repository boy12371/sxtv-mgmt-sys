package com.vms.framework.tabview;

import java.util.List;

public class TabElementBean {

	private String name;

	private String url;
	
	private String id;
	
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

	public void setSubTabs(List<TabElementBean> subTabs) {
		this.subTabs = subTabs;
	}

	public List<TabElementBean> getSubTabs() {
		return subTabs;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
