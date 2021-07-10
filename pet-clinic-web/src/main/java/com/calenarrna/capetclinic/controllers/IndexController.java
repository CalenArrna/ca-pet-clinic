package com.calenarrna.capetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"","/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/oups"})
    public String error () {
        return "notImplemented";
    }

}
