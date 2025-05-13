package com.locador.api.service.basics;


import com.locador.api.model.basics.PersonType;
import com.locador.api.repository.basics.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypeService {

    @Autowired
    private PersonTypeRepository personTypeRepository;

    public List<PersonType> findAll(){
        return personTypeRepository.findAll();
    }

    public Optional<PersonType> findById(Integer id){
        return personTypeRepository.findById(id);
    }

    public PersonType save(PersonType personType){
       return personTypeRepository.save(personType);
    }

    public PersonType update(Integer id, PersonType personType){
        if(!personTypeRepository.existsById(id)){
            throw new RuntimeException("personType não encontrado");
        }
        personType.setId(id);
        return personTypeRepository.save(personType);
    }

    public void delete(Integer id){
     if(!personTypeRepository.existsById(id)){
         throw new RuntimeException("personType não encontrada");
     }
     personTypeRepository.deleteById(id);
    }
}
