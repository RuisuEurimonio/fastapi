/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.service;

import fastApi.demo.CustomErrors.CustomException;
import fastApi.demo.Repository.CategoryRepository;
import fastApi.demo.models.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryR;
    
    public List<Category> getAllCategory(){
        return categoryR.getAllCategory();
    }
    
    public Category getById(Integer id){
        if(id == null) throw new CustomException("El id no se ingreso.");
        Category categoryDB = categoryR.getById(id).orElseThrow(()-> new CustomException("No se encontró ningúna categoria con id: "+id));
        return categoryDB;
    }
    
    public Category createCategory(Category category){
        return categoryR.createCategory(category);
    }
    
    public Category updateCategory(Category category){
        Category categoryDB = categoryR.getById(category.getId()).orElseThrow(() -> new CustomException("No se encontro la categoria a actualizar"));
        
        if(category.getName() != null) categoryDB.setName(category.getName());
        if(category.getDescription() != null) categoryDB.setDescription(category.getDescription());
        
        return categoryR.updateCategory(categoryDB);
    }
    
    public void deleteCategory(Integer id){
        categoryR.getById(id).orElseThrow(()-> new CustomException("No se encontro la categoria a eliminar"));
        categoryR.deleteByIdCategory(id);
    }
}
