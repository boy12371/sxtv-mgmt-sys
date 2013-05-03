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


@RequestMapping("/front/peoples")
@Controller
public class ControllerPeople {

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(People people, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		people.persist();
		return "redirect:/tvshows/create?toCreate";
	}

	@RequestMapping(params = "toCreate", produces = "text/html")
	public String initForm(Model uiModel) {
		uiModel.addAttribute("formURL", "/front/peoples/doCreate");
		uiModel.addAttribute("people", new People());
		return "peoples/create";
	}
	
	@RequestMapping(value = "/doAjaxCreate", method = RequestMethod.POST, produces = "text/html")
	public @ResponseBody String processAjaxSubmit(People people, Model uiModel, HttpServletRequest httpServletRequest){
		EntityManager em = People.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM People AS o WHERE o.name = :name");
		Query query = em.createQuery(sb.toString(), People.class);
		query.setParameter("name", people.getName());
		List<People> peoples = query.getResultList();
		if(null != peoples && !peoples.isEmpty()){
			return "FAILED_DUPLICATE";
		}
		people.persist();
		return "SUCCESS_"+people.getId();
	}
}
