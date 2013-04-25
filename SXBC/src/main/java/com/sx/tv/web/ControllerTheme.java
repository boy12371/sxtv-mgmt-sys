package com.sx.tv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.Theme;

@Controller
@RequestMapping(value = "/front/themes")
public class ControllerTheme {

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(Theme theme, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		theme.persist();
		return "redirect:/tvshows/create?toCreate";
	}

	@RequestMapping(params = "toCreate", produces = "text/html")
	public String initForm(Model uiModel) {
		uiModel.addAttribute("formURL", "/front/themes/doCreate");
		populateEditForm(uiModel, new Theme());
		return "themes/create";
	}

	public void populateEditForm(Model uiModel, Theme theme) {
		uiModel.addAttribute("theme", theme);
	}
}
