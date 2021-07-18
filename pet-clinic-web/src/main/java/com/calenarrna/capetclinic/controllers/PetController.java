package com.calenarrna.capetclinic.controllers;

import com.calenarrna.capetclinic.model.Owner;
import com.calenarrna.capetclinic.model.PetType;
import com.calenarrna.capetclinic.services.OwnerService;
import com.calenarrna.capetclinic.services.PetService;
import com.calenarrna.capetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService pets;
    private final OwnerService owners;
    private final PetTypeService petTypeService;

    public PetController(PetService pets, OwnerService owners, PetTypeService petTypeService) {
        this.pets = pets;
        this.owners = owners;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes (){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return owners.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder (WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
