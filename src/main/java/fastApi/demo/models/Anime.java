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
@Entity
@Table(name="anime")
public class Anime {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private boolean finished;
    
    @Column(nullable = true)
    private String image;
    
    @Column(nullable = true)
    private LocalDateTime year;
    
    @CreationTimestamp
    private LocalDateTime createDate;
    
    @UpdateTimestamp
    private LocalDateTime updateDate;
    
    @OneToMany(mappedBy ="anime")
    private List<CharacterM> characters;
    
    @ManyToMany
    @JoinTable(
            name="list_anime_category",
            joinColumns = @JoinColumn(name="anime"),
            inverseJoinColumns = @JoinColumn(name="category")   
    )
    private List<Category> categories;
}
