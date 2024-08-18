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
    
    @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 carácteres.")
    @NotBlank
    private String name;
    
    @Size(min = 5, max = 150, message = "La descripción debe tener entre 5 y 150 carácteres")
    @NotBlank
    private String description;
    
    @CreationTimestamp
    @Column(name = "createdate")
    private LocalDateTime createDate;
    
    @UpdateTimestamp
    @Column(name = "updatedate")
    private LocalDateTime updateDate;
    
    @ManyToMany(mappedBy = "categories")
    private List<Anime> animes;
    
}
