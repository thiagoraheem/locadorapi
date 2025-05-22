package com.locador.api.controller.basics;

import com.locador.api.model.basics.PersonType;
import com.locador.api.service.basics.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persontype")
@CrossOrigin
public class PersonTypeController {

    @Autowired
    private PersonTypeService personTypeService;

    @GetMapping
    public ResponseEntity<List<PersonType>> getAll(){
        try{
            List<PersonType> personTypes = personTypeService.findAll();
            if(personTypes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(personTypes);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonType> getById(@PathVariable Integer id){

        return personTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonType> create(@RequestBody PersonType personType){
         return ResponseEntity.ok(personTypeService.save(personType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonType> update(@PathVariable Integer id, @RequestBody PersonType personType){
        try {
            return ResponseEntity.ok(personTypeService.update(id, personType));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            personTypeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
