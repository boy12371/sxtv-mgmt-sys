package com.sx.tv.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.tv.entites.ProjectorComments;

@RequestMapping("/projectorcommentses")
@Controller
@RooWebScaffold(path = "projectorcommentses", formBackingObject = ProjectorComments.class)
public class ProjectorCommentsController {
}
