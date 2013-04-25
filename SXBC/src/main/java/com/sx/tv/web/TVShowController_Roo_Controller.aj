// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.web;

import com.sx.tv.entites.Company;
import com.sx.tv.entites.People;
import com.sx.tv.entites.Progress;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.Theme;
import com.sx.tv.entites.User;
import com.sx.tv.web.TVShowController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TVShowController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TVShowController.create(@Valid TVShow TVShow_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, TVShow_);
            return "tvshows1/create";
        }
        uiModel.asMap().clear();
        TVShow_.persist();
        return "redirect:/tvshows1/" + encodeUrlPathSegment(TVShow_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String TVShowController.createForm(Model uiModel) {
        populateEditForm(uiModel, new TVShow());
        return "tvshows1/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TVShowController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("tvshow_", TVShow.findTVShow(id));
        uiModel.addAttribute("itemId", id);
        return "tvshows1/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String TVShowController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tvshows", TVShow.findTVShowEntries(firstResult, sizeNo));
            float nrOfPages = (float) TVShow.countTVShows() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tvshows", TVShow.findAllTVShows());
        }
        addDateTimeFormatPatterns(uiModel);
        return "tvshows1/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TVShowController.update(@Valid TVShow TVShow_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, TVShow_);
            return "tvshows1/update";
        }
        uiModel.asMap().clear();
        TVShow_.merge();
        return "redirect:/tvshows1/" + encodeUrlPathSegment(TVShow_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TVShowController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, TVShow.findTVShow(id));
        return "tvshows1/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TVShowController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TVShow TVShow_ = TVShow.findTVShow(id);
        TVShow_.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tvshows1";
    }
    
    void TVShowController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("TVShow__inputdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("TVShow__rejectdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void TVShowController.populateEditForm(Model uiModel, TVShow TVShow_) {
        uiModel.addAttribute("TVShow_", TVShow_);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("peoples", People.findAllPeoples());
        uiModel.addAttribute("progresses", Progress.findAllProgresses());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        uiModel.addAttribute("themes", Theme.findAllThemes());
        uiModel.addAttribute("users", User.findAllUsers());
    }
    
    String TVShowController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
