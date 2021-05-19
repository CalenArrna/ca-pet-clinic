package com.calenarrna.capetclinic.services.map;

import com.calenarrna.capetclinic.model.Owner;
import com.calenarrna.capetclinic.model.Pet;
import com.calenarrna.capetclinic.services.OwnerService;
import com.calenarrna.capetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetMapService petMapService;

    public OwnerMapService(PetTypeService petTypeService, PetMapService petMapService) {
        this.petTypeService = petTypeService;
        this.petMapService = petMapService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.getById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            if (owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) pet.setPetType(petTypeService.save(pet.getPetType()));
                    } else throw new RuntimeException("PetType is required!");
                    if (pet.getId() == null) {
                        Pet savedPet = petMapService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(owner);
        } else return null;

    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
