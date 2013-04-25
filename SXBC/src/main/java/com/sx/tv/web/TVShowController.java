package com.sx.tv.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.tv.entites.TVShow;

@RequestMapping("/tvShows1")
@Controller
@RooWebScaffold(path = "tvshows1", formBackingObject = TVShow.class)
public class TVShowController {
}
