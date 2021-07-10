package com.calenarrna.capetclinic.controllers;

import com.calenarrna.capetclinic.model.Owner;
import com.calenarrna.capetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/find"})
    public String findOwners (Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }
    @GetMapping("")
    public String processFindForm (Owner owner, BindingResult result, Model model) {

        if (owner.getLastName() == null) owner.setLastName("");

        Set<Owner> setOfOwners = ownerService.findAllByLastNameLike(owner.getLastName());

        if (setOfOwners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if (setOfOwners.size() == 1) {
            owner = setOfOwners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }else {
            model.addAttribute("selections", setOfOwners);
            return "owners/ownersList";
        }

    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        Owner owner = ownerService.findById(ownerId);
        mav.addObject(owner);
        return mav;
    }
}
