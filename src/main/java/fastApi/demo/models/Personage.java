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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ruisu's
 */

@Data
@Entity
@Table(name="personage")
public class Personage {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(min = 3 , max = 50, message = "El nombre debe tener entre 3 a 50 carácteres", groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "Se necesita un nombre", groups = OnCreate.class)
    private String name;
    
    @ManyToOne()
    @JoinColumn(name="gender")
    @JsonIgnoreProperties(value="personages")
    private Gender gender;
    
    @Column(nullable = true)
    @Size(min = 10, max = 300, message = "La dirección debe de tener entre 10 a 300 carácteres", groups = {OnCreate.class, OnUpdate.class})
    private String image;
    
    @CreationTimestamp
    @Column(name = "createdate")
    private LocalDateTime createDate;
    
    @UpdateTimestamp
    @Column(name = "updatedate")
    private LocalDateTime updateTime;
    
    @ManyToOne()
    @JoinColumn(name="anime")
    @JsonIgnoreProperties(value="personage")
    private Anime anime;

}
