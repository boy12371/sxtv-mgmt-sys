package com.sx.tv.web;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx.tv.entites.People;
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
	
	@RequestMapping(value = "/doAjaxCreate", method = RequestMethod.POST, produces = "text/html")
	public @ResponseBody String processAjaxSubmit(Theme theme, Model uiModel, HttpServletRequest httpServletRequest){
		EntityManager em = Theme.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM Theme AS o WHERE o.name = :name");
		Query query = em.createQuery(sb.toString(), Theme.class);
		query.setParameter("name", theme.getName());
		List<Theme> themes = query.getResultList();
		if(null != themes && !themes.isEmpty()){
			return "FAILED_DUPLICATE";
		}
		theme.persist();
		return "SUCCESS_"+theme.getId();
	}
}
