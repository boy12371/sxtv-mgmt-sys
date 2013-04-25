package com.sx.tv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.ContractPayment;
import com.sx.tv.entites.TVContract;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/front/contractpayments")
@Controller
public class ControllerCP {
	@RequestMapping(value="/create",method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid ContractPayment contractPayment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, contractPayment);
			return "contractpayments/createPayment";
		}
		uiModel.asMap().clear();
		contractPayment.persist();
		TVContract tc = contractPayment.getContract();
		List<ContractPayment> ps = null;
		try {
			ps = ContractPayment.findContractPaymentsByContract(tc).getResultList();
			uiModel.addAttribute("contractPayments", ps);
		} catch (Exception e) {
			// TODO: handle exception
		}
		uiModel.addAttribute("contract", contractPayment.getContract());
		return "tvcontracts/showContract";
	}

	@RequestMapping(value = "/update/doUpdate", method = RequestMethod.PUT, produces = "text/html")
	public String doUpdate(@Valid ContractPayment contractPayment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, contractPayment);
			return "contractpayments/update";
		}
		uiModel.asMap().clear();
		contractPayment.merge();
		// return "redirect:/contractpayments/" +
		// encodeUrlPathSegment(contractPayment.getId().toString(),
		// httpServletRequest);
		return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(contractPayment.getContract().getTvshow().getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
	public String toUpdateForm(@PathVariable("id") Integer id, Model uiModel) {
		uiModel.addAttribute("formURL", "/front/contractpayments/update/doUpdate");
		populateEditForm(uiModel, ContractPayment.findContractPayment(id));
		return "contractpayments/update";
	}

	@RequestMapping(value = "/create/{conID}", params = "toCreate", produces = "text/html")
	public String initForm(@PathVariable("conID") Integer conID, Model uiModel) {
		ContractPayment cp = new ContractPayment();
		cp.setContract(TVContract.findTVContract(conID));
		uiModel.addAttribute("contractPayment", cp);
		addDateTimeFormatPatterns(uiModel);
		return "contractpayments/createPayment";
	}

	void populateEditForm(Model uiModel, ContractPayment contractPayment) {
		uiModel.addAttribute("contractPayment", contractPayment);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("tvcontracts", TVContract.findAllTVContracts());
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("contractPayment_paydate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("contractPayment_recivedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
	}
}
