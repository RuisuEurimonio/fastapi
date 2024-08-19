/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import fastApi.demo.Validations.OnCreate;
import fastApi.demo.Validations.OnUpdate;
import fastApi.demo.models.Anime;
import fastApi.demo.models.Category;
import fastApi.demo.service.AnimeService;
import fastApi.demo.service.CategoryService;
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
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryS.getAllCategory());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Category> createAnime(@Validated(OnCreate.class) @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryS.createCategory(category));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Category> updateAnime(@Validated(OnUpdate.class) @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryS.updateCategory(category));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnime(@Valid @PathVariable("id") Integer id){
        categoryS.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria con id "+id+" eliminado.");
    }
    
}
