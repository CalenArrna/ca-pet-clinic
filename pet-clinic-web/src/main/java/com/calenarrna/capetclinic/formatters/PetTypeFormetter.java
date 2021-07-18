package com.calenarrna.capetclinic.formatters;

import com.calenarrna.capetclinic.model.PetType;
import com.calenarrna.capetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormetter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormetter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }


    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) return type;
        }
        throw new ParseException("petType not found: " + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
