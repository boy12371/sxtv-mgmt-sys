package com.sx.tv.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Score;
import com.sx.tv.entites.User;

@RequestMapping("/scores")
@Controller
@RooWebScaffold(path = "scores", formBackingObject = Score.class)
public class ScoreController {

	void populateEditForm(Model uiModel, Score score) {
		uiModel.addAttribute("score", score);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		uiModel.addAttribute("recommendclasses", RecommendClass.findAllRecommendClasses());
		//uiModel.addAttribute("scoredetails", ScoreDetail.findAllScoreDetails());
		// uiModel.addAttribute("tvshows", TVShow.findAllTVShows());
		uiModel.addAttribute("users", User.findAllUsers());
	}
}
