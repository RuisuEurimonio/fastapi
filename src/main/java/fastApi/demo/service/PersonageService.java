/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.service;

import fastApi.demo.CustomErrors.CustomException;
import fastApi.demo.Repository.PersonageRepository;
import fastApi.demo.models.Personage;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class PersonageService {
    
    @Autowired
    private PersonageRepository personageR;
    
    public List<Personage> getAll(){
        return personageR.getAllCharacters();
    }
    
    public Personage getById(int id){
        Personage personageDB = personageR.getById(id).orElseThrow(()-> new CustomException("No se encontró ningún personaje con el id: "+id));
        return personageDB;
    }
    
    public Personage createPersonage(Personage personage){
        if(personage == null || personage.getName() == null || personage.getGender() == null || personage.getAnime() == null){
            throw new CustomException("Ingresa el nombre, genero y anime");
        }
        return personageR.createCharacter(personage);
    }
    
    public Personage updatePersonage(Personage personage){
        if(personage == null || personage.getId() == null || personage.getName() == null || personage.getGender() == null || personage.getAnime() == null){
            throw new CustomException("Verifica que los campos no sean nulos.");
        }
        return personageR.updateCharacter(personage);
    }
    
    public void deletePersonage(Integer id){
        if(id == null) throw new CustomException("El id no puede ser nulo.");
        personageR.deleteByIdCharacter(id);
    }
    
}
