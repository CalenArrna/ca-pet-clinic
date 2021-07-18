package com.calenarrna.capetclinic.controllers;

import com.calenarrna.capetclinic.model.Owner;
import com.calenarrna.capetclinic.model.Pet;
import com.calenarrna.capetclinic.model.PetType;
import com.calenarrna.capetclinic.services.OwnerService;
import com.calenarrna.capetclinic.services.PetService;
import com.calenarrna.capetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService owners;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService owners, PetTypeService petTypeService) {
        this.petService = petService;
        this.owners = owners;
        this.petTypeService = petTypeService;
    }

    @GetMapping("/pets/new")
    public String initCreation(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreation(Owner owner, Pet pet, ModelMap model, BindingResult bindingResult){
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (bindingResult.hasErrors()){
            model.put("pet",pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" +owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
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
