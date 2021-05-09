package com.calenarrna.capetclinic.services;

import com.calenarrna.capetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById (Long id);

    Vet save(Vet vet);

    Set<Vet> findAll ();
}
