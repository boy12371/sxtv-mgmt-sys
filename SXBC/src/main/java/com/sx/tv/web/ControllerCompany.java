package com.sx.tv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.Company;
import com.sx.tv.entites.People;


@Controller
@RequestMapping(value = "/front/companys")
public class ControllerCompany {

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(Company company, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		company.persist();
		People p = new People();
		p.setName(company.getPublisher());
		p.persist();
		return "redirect:/tvshows/create?toCreate";
	}

	@RequestMapping(params = "toCreate", produces = "text/html")
	public String initForm(Model uiModel) {
		uiModel.addAttribute("formURL", "/front/companys/doCreate");
		populateEditForm(uiModel, new Company());
		return "companys/create";
	}
	
	void populateEditForm(Model uiModel, Company company) {
        uiModel.addAttribute("company", company);
    }
}
