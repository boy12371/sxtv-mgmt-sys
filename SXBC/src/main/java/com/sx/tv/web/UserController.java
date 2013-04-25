package com.sx.tv.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sx.tv.entites.User;
import com.sx.tv.service.EncodeUserPassword;

@RequestMapping("/users")
@Controller
@RooWebScaffold(path = "users", formBackingObject = User.class)
public class UserController {

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, user);
			return "users/create";
		}
		uiModel.asMap().clear();
		user.setPassword(EncodeUserPassword.encrypt(user.getName(), user.getPassword()));
		user.persist();
		uiModel.addAttribute("users", User.findAllUsers());
		return "users/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, user);
			return "users/update";
		}
		uiModel.asMap().clear();
		User _u = User.findUser(user.getId());
		if (!_u.getPassword().equals(user.getPassword())) {
			user.setPassword(EncodeUserPassword.encrypt(user.getName(), user.getPassword()));
		}
		user.merge();
		uiModel.addAttribute("users", User.findAllUsers());
		return "users/list";
	}

	@RequestMapping(value = "passwdUpdate", produces = "text/html")
	public String passwdUpdate() {
		return "users/passwd";
	}

	@RequestMapping(value = "updatePasswd", produces = "text/html")
	public String updatePasswd(@RequestParam String passwd, Principal principal) {
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		logonUser.setPassword(EncodeUserPassword.encrypt(logonUser.getName(), passwd));
		logonUser.merge();
		return "index";
	}
}
