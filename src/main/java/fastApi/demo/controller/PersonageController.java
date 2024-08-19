/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import fastApi.demo.models.Personage;
import fastApi.demo.service.PersonageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ruisu's
 */
@RestController
@RequestMapping("/api/personages")
public class PersonageController {
    
    @Autowired
    private PersonageService personageS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Personage>> getAll(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personageS.getAll());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Personage> getById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personageS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Personage> createPersonage(@RequestBody Personage personage){
        return ResponseEntity.status(HttpStatus.CREATED).body(personageS.createPersonage(personage));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Personage> updatePersonage(@RequestBody Personage personage){
        return ResponseEntity.status(HttpStatus.CREATED).body(personageS.updatePersonage(personage));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePersonage(@PathVariable("id") Integer id){
        personageS.deletePersonage(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Personaje eliminado");
    }
}
