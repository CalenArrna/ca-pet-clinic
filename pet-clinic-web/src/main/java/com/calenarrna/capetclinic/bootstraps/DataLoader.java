package com.calenarrna.capetclinic.bootstraps;

import com.calenarrna.capetclinic.model.Owner;
import com.calenarrna.capetclinic.model.PetType;
import com.calenarrna.capetclinic.model.Vet;
import com.calenarrna.capetclinic.services.OwnerService;
import com.calenarrna.capetclinic.services.PetTypeService;
import com.calenarrna.capetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedPetType1 = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedPetType2 = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner2);

        System.out.println("Loaded Owners!");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Frodo");
        vet2.setLastName("Baggins");

        vetService.save(vet2);

        System.out.println("Loaded Vets!");
    }
}
