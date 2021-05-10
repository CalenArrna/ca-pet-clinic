package com.calenarrna.capetclinic.services;

import com.calenarrna.capetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName (String lastName);

}
