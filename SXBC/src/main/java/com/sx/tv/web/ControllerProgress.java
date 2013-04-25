package com.sx.tv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.Progress;


@RequestMapping("/front/progresses")
@Controller
public class ControllerProgress {
	
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(Progress progress, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		progress.persist();
		return "redirect:/tvshows/create?toCreate";
	}

	@RequestMapping(params = "toCreate", produces = "text/html")
	public String initForm(Model uiModel) {
		uiModel.addAttribute("formURL", "/front/progresses/doCreate");
		uiModel.addAttribute("progress", new Progress());
		return "progresses/create";
	}
}
