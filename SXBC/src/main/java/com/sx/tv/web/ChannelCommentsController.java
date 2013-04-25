package com.sx.tv.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.ChannelComments;
import com.sx.tv.entites.RecommendClass;

@RequestMapping("/channelcommentses")
@Controller
@RooWebScaffold(path = "channelcommentses", formBackingObject = ChannelComments.class)
public class ChannelCommentsController {
    void populateEditForm(Model uiModel, ChannelComments channelComments) {
        uiModel.addAttribute("channelComments", channelComments);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("channels", Channel.findAllChannels());
        List<RecommendClass> rcList = new ArrayList<RecommendClass>();
        RecommendClass rc = new RecommendClass(0, "无");
        rcList.add(rc);
        rcList.addAll(RecommendClass.findAllRecommendClasses());
        uiModel.addAttribute("recommendclasses", rcList);
    }
}
