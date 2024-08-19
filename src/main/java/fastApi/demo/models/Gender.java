/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Ruisu's
 */

@Data
@Table(name="gender")
@Entity
public class Gender {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private char letter;
    
    @Size(min = 7, max = 15, message = "El nombre debe tener entre 9 a 15 carácteres.")
    private String name;
    
    @Size(min = 10, max = 50, message = "La descripción debe tener entre 10 a 50 carácteres.")
    private String description;
    
    @OneToMany(mappedBy = "gender")
    private List<Personage> personages;
    
}
