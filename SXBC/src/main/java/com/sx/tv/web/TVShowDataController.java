package com.sx.tv.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.TVShowData;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/tvshowdatas")
@Controller
@RooWebScaffold(path = "tvshowdatas", formBackingObject = TVShowData.class)
public class TVShowDataController {

	@RequestMapping(value = "/{tvid}", method = RequestMethod.POST, produces = "text/html")
	public String create(@PathVariable("tvid") Long tvid, @Valid TVShowData TVShowData_,@RequestParam(required = false)String level, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, TVShowData_);
			return "tvshowdatas/create";
		}
		uiModel.asMap().clear();
		TVShow tv = TVShow.findTVShow(tvid);
		tv.setRanking(TVShowData_.getRanking());
		tv.setRatings(TVShowData_.getRating());
		tv.setMarketShare(TVShowData_.getMarketShare());
		tv.merge();
		
		//String tdata = TVShowData_.getTdata();
//		if(!tdata.equals("")){
//			String[] _array = tdata.split(";");
//			List<PlayData> pds = new ArrayList<PlayData>(_array.length);
//			for (String string : _array) {
//				String [] _a = string.split(",");
//				PlayData pd = new PlayData(_a[0],_a[1],_a[2],_a[3],_a[4],tv);
//				pd.persist();
//				pds.add(pd);
//			}
//		}
		if(null != level){
			return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(tv.getId().toString(), httpServletRequest) + "?level=level2market";
		}else{
			return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(tv.getId().toString(), httpServletRequest);	
		}
	}

	@RequestMapping(value = "/{tvid}", params = "form", produces = "text/html")
	public String createForm(@PathVariable("tvid") Long tvid,@RequestParam(required = false)String level, Model uiModel) {
		uiModel.addAttribute("tvid", tvid);
		uiModel.addAttribute("tvshow", TVShow.findTVShow(tvid));
		if(null != level && !"".equals(level)){
			uiModel.addAttribute("level", level);
		}
		uiModel.addAttribute("tvid", tvid);
		populateEditForm(uiModel, new TVShowData());
		return "tvshowdatas/create";
	}

	void populateEditForm(Model uiModel, TVShowData TVShowData_) {
		uiModel.addAttribute("TVShowData_", TVShowData_);
	}
}
