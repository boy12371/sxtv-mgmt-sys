package com.sx.tv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.People;

@RequestMapping("/peoples")
@Controller
@RooWebScaffold(path = "peoples", formBackingObject = People.class)
public class PeopleController {
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(People people, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		people.persist();
		return "redirect:/tvshows?toCreate";
	}

	@RequestMapping(params = "toCreate", produces = "text/html")
	public String initForm(Model uiModel) {
		uiModel.addAttribute("formURL", "/peoples/doCreate");
		populateEditForm(uiModel, new People());
		return "peoples/create";
	}
}
