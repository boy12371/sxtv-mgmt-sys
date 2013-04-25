package com.sx.tv.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.User;

@RequestMapping("/deptcommentses")
@Controller
@RooWebScaffold(path = "deptcommentses", formBackingObject = DeptComments.class)
public class DeptCommentsController {
	
	void populateEditForm(Model uiModel, DeptComments deptComments) {
		uiModel.addAttribute("deptComments", deptComments);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		List<RecommendClass> rcList = new ArrayList<RecommendClass>();
		RecommendClass rc = new RecommendClass(0, "无");
		rcList.add(rc);
		rcList.addAll(RecommendClass.findAllRecommendClasses());
		uiModel.addAttribute("recommendclasses", rcList);
		// uiModel.addAttribute("tvshows", TVShow.findAllTVShows());
		uiModel.addAttribute("users", User.findAllUsers());
	}
}
