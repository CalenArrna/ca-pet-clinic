package com.calenarrna.capetclinic.services;

import com.calenarrna.capetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName (String lastName);

    Set<Owner> findAllByLastNameLike(String lastName);
}
