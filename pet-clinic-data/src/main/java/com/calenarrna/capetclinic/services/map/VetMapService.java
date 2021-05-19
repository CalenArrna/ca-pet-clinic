package com.calenarrna.capetclinic.services.map;

import com.calenarrna.capetclinic.model.Specialty;
import com.calenarrna.capetclinic.model.Vet;
import com.calenarrna.capetclinic.services.SpecialtyService;
import com.calenarrna.capetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.getById(id);
    }

    @Override
    public Vet save(Vet vet) {
        if (vet.getSpecialities().size() > 0){
            vet.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null){
                    Specialty savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
