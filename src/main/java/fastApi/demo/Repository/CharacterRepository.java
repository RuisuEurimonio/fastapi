/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.Repository;

import fastApi.demo.JpaRepository.CharacterJpaRepository;
import fastApi.demo.models.CharacterM;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class CharacterRepository {
    
    @Autowired
    private CharacterJpaRepository categoryJR;
    
    public List<CharacterM> getAllCharacters(){
        return categoryJR.findAll();
    }
    
    public Optional<CharacterM> getById(int id){
        return categoryJR.findById(id);
    }
    
    public CharacterM createCharacter(CharacterM character){
        return categoryJR.save(character);
    }
    
    public CharacterM updateCharacter(CharacterM character){
        return categoryJR.save(character);
    }
    
    public void deleteByIdCharacter(int id){
        categoryJR.deleteById(id);
    }
}

