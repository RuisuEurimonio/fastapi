/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import fastApi.demo.Validations.OnCreate;
import fastApi.demo.Validations.OnUpdate;
import fastApi.demo.models.Gender;
import fastApi.demo.service.GenderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/genders")
public class GenderController {
    
    @Autowired
    private GenderService genderS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Gender>> getAll(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genderS.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Gender> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genderS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Gender> createGender(@Validated(OnCreate.class) @RequestBody Gender gender){
        return ResponseEntity.status(HttpStatus.CREATED).body(genderS.createGender(gender));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Gender> updateGender(@Validated(OnUpdate.class) @RequestBody Gender gender){
        return ResponseEntity.status(HttpStatus.CREATED).body(genderS.updateGender(gender));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGender(@PathVariable("id") int id){
        genderS.deleteGender(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Genero eliminado");
    }
    
}
