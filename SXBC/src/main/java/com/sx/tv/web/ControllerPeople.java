package com.sx.tv.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx.tv.entites.People;
import com.sx.tv.finder.TVShowsFinder;


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
	
	@RequestMapping(value = "list", produces = "text/html")
    public String list(@RequestParam(value = "svalue", required = false) String svalue,	
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("peoples", People.findPeopleEntries(firstResult, sizeNo));
            float nrOfPages = (float) People.countPeoples() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
        	if(null != svalue && !svalue.equals("")){
        		String qlString = "SELECT o FROM People AS o WHERE o.name LIKE :name";
        		EntityManager em = People.entityManager();
        		TypedQuery<People> query = em.createQuery(qlString,People.class);
        		try {
					svalue = new String(svalue.getBytes("ISO-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		query.setParameter("name", TVShowsFinder.convertLikeString(svalue));
        		uiModel.addAttribute("peoples", query.getResultList());
        	}else{
        		uiModel.addAttribute("peoples", People.findAllPeoples());	
        	}
        }
        return "peoples/list";
    }
}
