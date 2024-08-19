/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String name;
    
    @ManyToOne()
    @JoinColumn(name="gender")
    @JsonIgnoreProperties(value="personages")
    private Gender gender;
    
    @Column(nullable = true)
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
