package com.sx.tv.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.tv.entites.Type;

@RequestMapping("/types")
@Controller
@RooWebScaffold(path = "types", formBackingObject = Type.class)
public class TypeController {
	
}
