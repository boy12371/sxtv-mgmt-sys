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

import com.sx.tv.entites.Theme;
import com.sx.tv.finder.TVShowsFinder;

@Controller
@RequestMapping(value = "/front/themes")
public class ControllerTheme {

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(Theme theme, Model uiModel,
			HttpServletRequest httpServletRequest) {
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
	public @ResponseBody
	String processAjaxSubmit(Theme theme, Model uiModel,
			HttpServletRequest httpServletRequest) {
		EntityManager em = Theme.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM Theme AS o WHERE o.name = :name");
		Query query = em.createQuery(sb.toString(), Theme.class);
		query.setParameter("name", theme.getName());
		List<Theme> themes = query.getResultList();
		if (null != themes && !themes.isEmpty()) {
			return "FAILED_DUPLICATE";
		}
		theme.persist();
		return "SUCCESS_" + theme.getId();
	}

	@RequestMapping(value = "list", produces = "text/html")
	public String list(
			@RequestParam(value = "svalue", required = false) String svalue,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("themes",
					Theme.findThemeEntries(firstResult, sizeNo));
			float nrOfPages = (float) Theme.countThemes() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {

			if (null != svalue && !svalue.equals("")) {
				String qlString = "SELECT o FROM Theme AS o WHERE o.name LIKE :name";
				EntityManager em = Theme.entityManager();
				TypedQuery<Theme> query = em.createQuery(qlString, Theme.class);
				try {
					svalue = new String(svalue.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				query.setParameter("name",
						TVShowsFinder.convertLikeString(svalue));
				uiModel.addAttribute("themes", query.getResultList());
			} else {
				uiModel.addAttribute("themes", Theme.findAllThemes());
			}
		}
		return "themes/list";
	}
}
