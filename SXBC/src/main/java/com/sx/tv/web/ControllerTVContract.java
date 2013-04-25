package com.sx.tv.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.ContractPayment;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.User;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/front/tvcontracts")
@Controller
public class ControllerTVContract {

	private static final Logger logger = Logger.getLogger(ControllerTVContract.class);

	@RequestMapping(value="create",method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid TVContract TVContract_, BindingResult bindingResult, Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, TVContract_);
			return "tvcontracts/createContract";
		}
		uiModel.asMap().clear();
		TVContract_.persist();
		TVShow tv = TVContract_.getTvshow();
		Status st = Status.findStatus(StatusUtil.DAI_BO);
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		logger.info("Warning:Status Changed==========================\r\n");
		logger.info("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName() + ") status changed from \r\n" + tv.getStatus().getName() + "("
				+ tv.getStatus().getId() + ") to " + st.getName() + "(" + st.getId() + ") by user:(" + logonUser.getStaff() + "[" + logonUser.getName() + "="
				+ logonUser.getId() + "])\r\n");
		logger.info("Warning:Status Changed==========================\r\n");

		tv.setStatus(st);
		tv.setIsPurchase(true);
		tv.merge();
		uiModel.addAttribute("contract", TVContract_);
		uiModel.addAttribute("itemId", TVContract_.getId());
		List<ContractPayment> ps = null;
		try {
			ps = ContractPayment.findContractPaymentsByContract(TVContract_).getResultList();
			uiModel.addAttribute("contractPayments", ps);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "tvcontracts/showContract";
	}

	@RequestMapping(value = "/create/{tvid}", params = "toCreate", produces = "text/html")
	public String createForm(@PathVariable("tvid") long tvid, Principal principal, Model uiModel) {
		TVShow tv = TVShow.findTVShow(tvid);
		TVContract ctc = new TVContract();
		ctc.setTvshow(tv);
		uiModel.addAttribute("TVContract_", ctc);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		addDateTimeFormatPatterns(uiModel);
		return "tvcontracts/createContract";
	}
	
    @RequestMapping(value="/update/doUpdate",method = RequestMethod.PUT, produces = "text/html")
    public String doUpdate(@Valid TVContract TVContract_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, TVContract_);
            return "tvcontracts/update";
        }
        uiModel.asMap().clear();
        TVContract_.merge();
        return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(TVContract_.getTvshow().getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
    public String toUpdateForm(@PathVariable("id") Integer id, Model uiModel) {
    	uiModel.addAttribute("formURL", "/front/tvcontracts/update/doUpdate");
        populateEditForm(uiModel, TVContract.findTVContract(id));
        return "tvcontracts/update";
    }
    
    void populateEditForm(Model uiModel, TVContract TVContract_) {
        uiModel.addAttribute("TVContract_", TVContract_);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("channels", Channel.findAllChannels());
        uiModel.addAttribute("tvshows", TVShow.findAllTVShows());
    }
    void addDateTimeFormatPatterns(Model uiModel){
    	 uiModel.addAttribute("TVContract__showdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__recievedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__inputdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__filedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__recieveagreementdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__inputagreementdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__fileagreementdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__copyrightstart_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         uiModel.addAttribute("TVContract__copyrightend_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
         
    }
}
