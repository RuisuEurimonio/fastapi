/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.Repository;

import fastApi.demo.models.Personage;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import fastApi.demo.JpaRepository.PersonageJpaRepository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class PersonageRepository {
    
    @Autowired
    private PersonageJpaRepository categoryJR;
    
    public List<Personage> getAllCharacters(){
        return categoryJR.findAll();
    }
    
    public Optional<Personage> getById(int id){
        return categoryJR.findById(id);
    }
    
    public Personage createCharacter(Personage character){
        return categoryJR.save(character);
    }
    
    public Personage updateCharacter(Personage character){
        return categoryJR.save(character);
    }
    
    public void deleteByIdCharacter(int id){
        categoryJR.deleteById(id);
    }
}

