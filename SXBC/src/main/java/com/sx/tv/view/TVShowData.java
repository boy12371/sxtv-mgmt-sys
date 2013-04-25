package com.sx.tv.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class TVShowData {

    @Value("0")
    private Float rating;

    @Value("0")
    private Float marketShare;

    @Value("0")
    private Integer ranking;
    
    @Value("")
    private String tdata;

	public String getTdata() {
		return tdata;
	}

	public void setTdata(String tdata) {
		this.tdata = tdata;
	}
}
