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

import com.sx.tv.entites.Company;
import com.sx.tv.entites.People;


@Controller
@RequestMapping(value = "/front/companys")
public class ControllerCompany {

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(Company company, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		if(null != company.getPublisher() && !"".equals(company.getPublisher())){
			EntityManager em = People.entityManager();
			StringBuffer sb = new StringBuffer("SELECT o FROM People AS o WHERE o.name = :name");
			Query query = em.createQuery(sb.toString(), People.class);
			query.setParameter("name", company.getPublisher());
			List<People> p = query.getResultList();
			if(null == p || p.isEmpty()){
				People newp = new People();
				newp.setName(company.getPublisher());
				newp.persist();
			}
		}
		company.persist();
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
	
	@RequestMapping(value = "/doAjaxCreate", method = RequestMethod.POST, produces = "text/html")
	public @ResponseBody String processAjaxSubmit(Company company, Model uiModel, HttpServletRequest httpServletRequest){
		EntityManager em = Company.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM Company AS o WHERE o.name = :name");
		Query query = em.createQuery(sb.toString(), Company.class);
		query.setParameter("name", company.getName());
		List<Company> comList = query.getResultList();
		if(null != comList && !comList.isEmpty()){
			return "FAILED_DUPLICATE";
		}
		if(null != company.getPublisher() && !"".equals(company.getPublisher())){
			em = null;
			query = null;
			em = People.entityManager();
			StringBuffer _sb = new StringBuffer("SELECT o FROM People AS o WHERE o.name = :name");
			query = em.createQuery(_sb.toString(), People.class);
			query.setParameter("name", company.getPublisher());
			List<People> p = query.getResultList();
			if(null == p || p.isEmpty()){
				People newp = new People();
				newp.setName(company.getPublisher());
				newp.persist();
			}
		}
		company.persist();
		return "SUCCESS_"+company.getId();
	}
}
