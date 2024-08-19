/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fastApi.demo.Validations.OnCreate;
import fastApi.demo.Validations.OnUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ruisu's
 */
@Data
@Table(name="category")
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 carácteres.", groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(message = "El nombre no debe estar vacío", groups = OnCreate.class)
    private String name;
    
    @Size(min = 5, max = 150, message = "La descripción debe tener entre 5 y 150 carácteres.", groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(message = "La descripción no debe estar vacío", groups = OnCreate.class)
    private String description;
    
    @CreationTimestamp
    @Column(name = "createdate")
    private LocalDateTime createDate;
    
    @UpdateTimestamp
    @Column(name = "updatedate")
    private LocalDateTime updateDate;
    
    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties(value="categories")
    private List<Anime> animes;
    
}
