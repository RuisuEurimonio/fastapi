/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.models;

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
import jakarta.validation.constraints.Pattern;
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
    
    @NotNull
    @NotBlank
    @Size(min = 3, max = 150, message = "El nombre debe estar entre 3 y 150 caracteres.")
    private String name;
    
    
    private boolean finished;
    
    @Column(nullable = true)
    @Size(min = 10, message = "El link de la imagen no es valido.")
    private String image;
    
    @Column(nullable = true)
    private LocalDateTime year;
    
    @CreationTimestamp
    @Column(name="createdate")
    private LocalDateTime createDate;
    
    @UpdateTimestamp
    @Column(name="updatedate")
    private LocalDateTime updateDate;
    
    @OneToMany(mappedBy ="anime")
    private List<Personage> personage;
    
    @ManyToMany
    @JoinTable(
            name="list_anime_category",
            joinColumns = @JoinColumn(name="anime"),
            inverseJoinColumns = @JoinColumn(name="category")   
    )
    private List<Category> categories;
}
