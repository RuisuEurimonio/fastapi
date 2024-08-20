/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import fastApi.demo.Validations.OnCreate;
import fastApi.demo.Validations.OnUpdate;
import fastApi.demo.models.Anime;
import fastApi.demo.service.AnimeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ruisu's
 */
@RestController
@RequestMapping("/api/animes")
public class AnimeController {
    
    @Autowired
    private AnimeService animeS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Anime>> getAllAnimes(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(animeS.getAllAnime());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Anime> getById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(animeS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Anime> createAnime(@Validated(OnCreate.class) @RequestBody Anime anime){
        return ResponseEntity.status(HttpStatus.CREATED).body(animeS.createAnime(anime));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Anime> updateAnime(@Validated(OnUpdate.class) @RequestBody Anime anime){
        return ResponseEntity.status(HttpStatus.CREATED).body(animeS.updateAnime(anime));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnime(@PathVariable("id") Integer id){
        animeS.deleteByIdAnime(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Anime con id "+id+" eliminado.");
    }
}
