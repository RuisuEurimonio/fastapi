/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.service;

import fastApi.demo.CustomErrors.CustomException;
import fastApi.demo.Repository.CharacterRepository;
import fastApi.demo.models.CharacterM;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class CharacterService {
    
    @Autowired
    private CharacterRepository characterR;
    
    public List<CharacterM> getAll(){
        return characterR.getAllCharacters();
    }
    
    public Optional<CharacterM> getById(int id){
        characterR.getById(id).orElseThrow(()-> new CustomException("No se encontró ningún personaje con el id: "+id));
        return characterR.getById(id);
    }
    
    
    
}
