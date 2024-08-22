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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
/**
 *
 * @author Ruisu's
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name="anime")
public class Anime {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "El nombre es necesario", groups = OnCreate.class)
    @NotBlank(message = "El nombre es necesario", groups = OnCreate.class)
    @Size(min = 3, max = 150, message = "El nombre debe estar entre 3 y 150 caracteres.", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    
    @NotNull(message = "El estado es necesario", groups = OnCreate.class)
    private Boolean finished;
    
    @Column(nullable = true)
    @Size(min = 10, message = "El link de la imagen no es valido.", groups = {OnCreate.class, OnUpdate.class})
    private String image;
    
    @Column(nullable = true, name = "\"year\"")
    private LocalDateTime year;
    
    @CreationTimestamp
    @Column(name="createdate")
    private LocalDateTime createDate;
    
    @UpdateTimestamp
    @Column(name="updatedate")
    private LocalDateTime updateDate;
    
    @OneToMany(mappedBy ="anime")
    @JsonIgnoreProperties(value="anime")
    private List<Personage> personage;
    
    @ManyToMany
    @JoinTable(
            name="list_anime_category",
            joinColumns = @JoinColumn(name="anime"),
            inverseJoinColumns = @JoinColumn(name="category")   
    )
    @JsonIgnoreProperties(value="animes")
    private List<Category> categories;
}
