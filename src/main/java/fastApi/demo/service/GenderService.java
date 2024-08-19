/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.service;

import fastApi.demo.CustomErrors.CustomException;
import fastApi.demo.Repository.GenderRepository;
import fastApi.demo.models.Gender;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class GenderService {
    
    @Autowired
    private GenderRepository genderR;
    
    public List<Gender> getAll(){
        return genderR.getAllGender();
    }
    
    public Gender getById(int id){
        Gender genderDB = genderR.getByIdGender(id).orElseThrow(()-> new CustomException("No se encontró ningún genero con el id: "+id));
        return genderDB;
    }
    
    public Gender createGender(Gender gender){
        return genderR.createGender(gender);
    }
    
    public Gender updateGender(Gender gender){
        Gender genderDB = genderR.getByIdGender(gender.getId()).orElseThrow(()-> new CustomException("No se encontró ningún genero con el id: "+gender.getId()));
        
        if(gender.getLetter() != null) genderDB.setLetter(gender.getLetter());
        if(gender.getName() != null) genderDB.setName(gender.getName());
        if(gender.getDescription() != null) genderDB.setDescription(gender.getDescription());
        
        return genderR.updateGender(genderDB);
    }
    
    public void deleteGender(int id){
        genderR.getByIdGender(id).orElseThrow(()-> new CustomException("No se encontró ningún genero con el id: "+id));
        genderR.deleteByIdGender(id);
    }
}
