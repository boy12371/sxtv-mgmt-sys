package com.sx.tv.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.ChannelComments;
import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.ProjectorComments;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/front/channelcommentses")
@Controller
public class ControllerChnlCmts {
	 @RequestMapping(value="create",method = RequestMethod.POST, produces = "text/html")
	    public String create(@Valid ChannelComments channelComments, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, channelComments);
	            return "channelcommentses/createCC";
	        }
	        uiModel.asMap().clear();
	        channelComments.setRecordDate(new Date());
	        channelComments.persist();
	        return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(channelComments.getTvshow().getId().toString(), httpServletRequest);
	    }

	    @RequestMapping(value = "/create/{deptid}", params = "toCreate", produces = "text/html")
	    public String createForm(@PathVariable("deptid") Integer deptid, Model uiModel) {
	        DeptComments deptCmt = DeptComments.findDeptComments(deptid);
	        ChannelComments cc = new ChannelComments();
	        cc.setChannel(deptCmt.getRecommendChannel());
	        cc.setTvshow(deptCmt.getTvshow());
	        populateEditForm(uiModel, cc);
	        return "channelcommentses/createCC";
	    }

	    @RequestMapping(value = "/recommendFrom/{id}", produces = "text/html")
	    public String recommendForm(@PathVariable("id") Integer id, Model uiModel) {
	    	
	        DeptComments deptCmt = DeptComments.findDeptComments(id);
	        ChannelComments cc = new ChannelComments();
	        cc.setChannel(deptCmt.getRecommendChannel());
	        cc.setTvshow(deptCmt.getTvshow());
	        List<ProjectorComments> prjCmts = ProjectorComments.findProjectorCommentsesByTvshow(deptCmt.getTvshow()).getResultList();
	        for (ProjectorComments p : prjCmts) {
				if(p.getRecommendChannel().getId() == deptCmt.getRecommendChannel().getId()){
					uiModel.addAttribute("prjCmt", p);
				}
			}
	        uiModel.addAttribute("deptCmt", deptCmt);
	        populateEditForm(uiModel, cc);
	        return "channelcommentses/recommendForm";
	    }

	    @RequestMapping(value="/update/doUpdate",method = RequestMethod.PUT, produces = "text/html")
	    public String doUpdate(@Valid ChannelComments channelComments, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, channelComments);
	            return "channelcommentses/update";
	        }
	        uiModel.asMap().clear();
	        channelComments.merge();
	        //return "redirect:/channelcommentses/" + encodeUrlPathSegment(channelComments.getId().toString(), httpServletRequest);
	        return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(channelComments.getTvshow().getId().toString(), httpServletRequest);
	    }
	    
	    @RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
	    public String toUpdateForm(@PathVariable("id") Integer id, Model uiModel) {
	        populateEditForm(uiModel, ChannelComments.findChannelComments(id));
	        uiModel.addAttribute("formURL", "/front/channelcommentses/update/doUpdate");
	        return "channelcommentses/update";
	    }
	    
	    void populateEditForm(Model uiModel, ChannelComments channelComments) {
	        uiModel.addAttribute("channelComments", channelComments);
	        uiModel.addAttribute("channelComments_recorddate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
	        uiModel.addAttribute("channelComments_recommenddate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
	        uiModel.addAttribute("channelComments_replydate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
	        uiModel.addAttribute("channels", Channel.findAllChannels());
	        List<RecommendClass> rcList = new ArrayList<RecommendClass>();
	        RecommendClass rc = new RecommendClass(0, "无");
	        rcList.add(rc);
	        rcList.addAll(RecommendClass.findAllRecommendClasses());
	        uiModel.addAttribute("recommendclasses", rcList);
	    }
}
