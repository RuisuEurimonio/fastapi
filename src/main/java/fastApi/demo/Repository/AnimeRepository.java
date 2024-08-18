/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.Repository;

import fastApi.demo.JpaRepository.AnimeJpaRepository;
import fastApi.demo.models.Anime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class AnimeRepository {
    
    @Autowired
    private AnimeJpaRepository animeJR;
    
    public List<Anime> getAllAnime(){
        return animeJR.findAll();
    }
    
    public Optional<Anime> getById(int id){
        return  animeJR.findById(id);
    }
    
    public Anime createAnime(Anime anime){
        return animeJR.save(anime);
    }
    
    public Anime updateAnime(Anime anime){
        return animeJR.save(anime);
    }
    
    public void deleteByIdAnime(int id){
        animeJR.deleteById(id);
    }
    
}
