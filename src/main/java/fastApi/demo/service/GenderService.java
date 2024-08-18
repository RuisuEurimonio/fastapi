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
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class GenderService {
    
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
        genderR.getByIdGender(gender.getId()).orElseThrow(()-> new CustomException("No se encontró ningún genero con el id: "+gender.getId()));
        return genderR.updateGender(gender);
    }
    
    public void deleteGender(int id){
        genderR.getByIdGender(id).orElseThrow(()-> new CustomException("No se encontró ningún genero con el id: "+id));
        genderR.deleteByIdGender(id);
    }
}
