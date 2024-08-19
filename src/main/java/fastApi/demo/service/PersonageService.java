/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.service;

import fastApi.demo.CustomErrors.CustomException;
import fastApi.demo.Repository.AnimeRepository;
import fastApi.demo.Repository.GenderRepository;
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
    
    @Autowired
    private AnimeRepository animeR;
    
    @Autowired
    private GenderRepository genderR;
    
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
        validateRelations(personage);
        return personageR.createCharacter(personage);
    }
    
    public Personage updatePersonage(Personage personage){
        if(personage == null || personage.getId() == null || personage.getName() == null || personage.getGender() == null || personage.getAnime() == null){
            throw new CustomException("Verifica que los campos no sean nulos.");
        }
        Personage personageDB = personageR.getById(personage.getId()).orElseThrow(()-> new CustomException("El personaje a modificar no se encontro"));
        personage.setCreateDate(personageDB.getCreateDate());
        validateRelations(personage);
        return personageR.updateCharacter(personage);
    }
    
    public void deletePersonage(Integer id){
        personageR.getById(id).orElseThrow(()-> new CustomException("No se encontro ningun personaje con id: "+id));
        personageR.deleteByIdCharacter(id);
    }
    
    public void validateRelations(Personage personage){
        animeR.getById(personage.getAnime().getId()).orElseThrow(()-> new CustomException("No se encontro anime con el id: " + personage.getAnime().getId()));
        genderR.getByIdGender(personage.getGender().getId()).orElseThrow(()-> new CustomException("No se encontro genero con el id: "+personage.getGender().getId()));
    }
    
}
