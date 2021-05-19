package com.calenarrna.capetclinic.repositories;

import com.calenarrna.capetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
