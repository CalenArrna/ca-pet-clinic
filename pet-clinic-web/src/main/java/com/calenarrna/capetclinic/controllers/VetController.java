package com.calenarrna.capetclinic.controllers;

import com.calenarrna.capetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html","/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

}
