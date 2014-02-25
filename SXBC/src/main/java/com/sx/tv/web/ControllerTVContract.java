package com.sx.tv.web;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.ContractPayment;
import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.User;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/front/tvcontracts")
@Controller
public class ControllerTVContract {

	private static final Logger logger = Logger
			.getLogger(ControllerTVContract.class);

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid TVContract TVContract_,
			BindingResult bindingResult, Principal principal, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, TVContract_);
			return "tvcontracts/createContract";
		}
		uiModel.asMap().clear();
		if (null == TVContract_.getCopyrightStart()) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			TVContract_.setCopyrightStart(c.getTime());
		}
		TVContract_.persist();
		TVShow tv = TVContract_.getTvshow();
		Status st = Status.findStatus(StatusUtil.DAI_BO);
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		logger.info("Warning:Status Changed==========================\r\n");
		logger.info("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName()
				+ ") status changed from \r\n" + tv.getStatus().getName() + "("
				+ tv.getStatus().getId() + ") to " + st.getName() + "("
				+ st.getId() + ") by user:(" + logonUser.getStaff() + "["
				+ logonUser.getName() + "=" + logonUser.getId() + "])\r\n");
		logger.info("Warning:Status Changed==========================\r\n");

		tv.setStatus(st);
		tv.setIsPurchase(true);
		tv.merge();
		uiModel.addAttribute("contract", TVContract_);
		uiModel.addAttribute("itemId", TVContract_.getId());
		List<ContractPayment> ps = null;
		try {
			ps = ContractPayment.findContractPaymentsByContract(TVContract_)
					.getResultList();
			uiModel.addAttribute("contractPayments", ps);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "tvcontracts/showContract";
	}

	@RequestMapping(value = "/create/{tvid}", params = "toCreate", produces = "text/html")
	public String createForm(@PathVariable("tvid") long tvid,
			Principal principal, Model uiModel) {
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		TVShow tv = TVShow.findTVShow(tvid);
		TVContract ctc = new TVContract();
		ctc.setTvshow(tv);
		ctc.setInputDate(new Date());
		ctc.setInputter(logonUser.getStaff());
		ctc.setRecieveOwner("李蕾");
		ctc.setInputter("王亮");
		ctc.setFileBy("李蕾");
		ctc.setRecieveAgreementOwner("李蕾");
		ctc.setInputterAgreement("王亮");
		ctc.setFileByAgreement("李蕾");

		uiModel.addAttribute("TVContract_", ctc);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		addDateTimeFormatPatterns(uiModel);
		return "tvcontracts/createContract";
	}

	@RequestMapping(value = "/update/doUpdate", method = RequestMethod.PUT, produces = "text/html")
	public String doUpdate(@Valid TVContract TVContract_,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, TVContract_);
			return "tvcontracts/update";
		}
		uiModel.asMap().clear();
		TVContract_.merge();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(TVContract_.getTvshow()
						.getId().toString(), httpServletRequest);
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

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute(
				"TVContract__showdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__recievedate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__inputdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__filedate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__recieveagreementdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__inputagreementdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__fileagreementdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__copyrightstart_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVContract__copyrightend_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));

	}

	@RequestMapping("/{tvid}/{tcid}/updateDate")
	public String updateStatusByPlayDate(
			@PathVariable("tvid") Long tvid,
			@PathVariable("tcid") Integer tcid,
			@org.springframework.format.annotation.DateTimeFormat(iso = ISO.DATE) Date sdate,
			@org.springframework.format.annotation.DateTimeFormat(iso = ISO.DATE) Date rdate,
			@org.springframework.format.annotation.DateTimeFormat(iso = ISO.DATE) Date wdate,
			Principal principal, HttpServletRequest httpServletRequest) {
		TVContract tc = TVContract.findTVContract(tcid);
		if (null != sdate) {
			tc.setTapeStoreDate(sdate);
		}
		if (null != rdate) {
			tc.setRecieveDate(rdate);
		}
		if (null != wdate) {
			tc.setShowDate(wdate);
		}
		boolean flag = null == tc.getCopyrightEnd();
		/*
		 * 依据哪个日期延期 0=入库日期 1=母带接收日期 2=上星日期
		 */
		switch (tc.getExtension()) {
		case 0:
			// 入库日期
			if (null != sdate) {
				tc.setTapeStoreDate(sdate);
				if (flag) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdate);
					cal.add(Calendar.YEAR, tc.getExYears());
					tc.setCopyrightEnd(cal.getTime());
				}
			}
			break;
		case 1:
			// 母带接收日期
			if (null != rdate) {
				tc.setRecieveDate(rdate);
				flag = null == tc.getCopyrightEnd();
				if (flag) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(rdate);
					cal.add(Calendar.YEAR, tc.getExYears());
					tc.setCopyrightEnd(cal.getTime());
				}
			}
			break;

		default:
			// 上星日期
			if (null != wdate) {
				tc.setShowDate(wdate);
				flag = null == tc.getCopyrightEnd();
				if (flag) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(wdate);
					cal.add(Calendar.YEAR, tc.getExYears());
					tc.setCopyrightEnd(cal.getTime());
				}
			}
			break;
		}
		tc.merge();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(tvid.toString(),
						httpServletRequest) + "?level=reciveStore";
	}
}
