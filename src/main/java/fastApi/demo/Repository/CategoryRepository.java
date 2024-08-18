/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.Repository;

import fastApi.demo.JpaRepository.CategoryJpaRepository;
import fastApi.demo.models.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class CategoryRepository {
    
    @Autowired
    private CategoryJpaRepository categoryJR;
    
    public List<Category> getAllCategory(){
        return categoryJR.findAll();
    }
    
    public Optional<Category> getById(int id){
        return categoryJR.findById(id);
    }
    
    public Category createCategory(Category category){
        return categoryJR.save(category);
    }
    
    public Category updateCategory(Category category){
        return categoryJR.save(category);
    }
    
    public void deleteByIdCategory(int id){
        categoryJR.deleteById(id);
    }
}
