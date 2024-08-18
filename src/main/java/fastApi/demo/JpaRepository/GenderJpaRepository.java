/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.JpaRepository;

import fastApi.demo.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruisu's
 */
public interface GenderJpaRepository extends JpaRepository<Gender, Integer>{
    
}
