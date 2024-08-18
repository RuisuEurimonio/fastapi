/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.Repository;

import fastApi.demo.JpaRepository.GenderJpaRepository;
import fastApi.demo.models.Gender;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class GenderRepository {
    
    @Autowired
    private GenderJpaRepository genderJR;
    
    public List<Gender> getAllGender(){
        return genderJR.findAll();
    }
    
    public Optional<Gender> getByIdGender(int id){
        return genderJR.findById(id);
    }
    
    public Gender createGender(Gender gender){
        return genderJR.save(gender);
    }
    
    public Gender updateGender(Gender gender){
        return genderJR.save(gender);
    }
    
    public void deleteByIdGender(int id){
        genderJR.deleteById(id);
    }
    
}
