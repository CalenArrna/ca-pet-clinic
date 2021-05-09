package com.calenarrna.capetclinic.services;

import com.calenarrna.capetclinic.model.Pet;

import java.util.Set;

public interface PetServices {
    Pet findById (Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
