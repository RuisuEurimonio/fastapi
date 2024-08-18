/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.service;

import fastApi.demo.CustomErrors.CustomException;
import fastApi.demo.Repository.AnimeRepository;
import fastApi.demo.models.Anime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class AnimeService {
    
    @Autowired
    private AnimeRepository animeR;
    
    public List<Anime> getAllAnime(){
        return animeR.getAllAnime();
    }
    
    public Anime getById(Integer id){
        if(id == null) throw new CustomException("El ud es nulo");
        Anime animeDB = animeR.getById(id).orElseThrow(()-> new CustomException("No se encontró ningún anime con id: "+id));
        return animeDB;
    }
    
    public Anime createAnime(Anime anime){
        if(anime == null) throw new CustomException("No se ingresaron datos.");
        return animeR.createAnime(anime);
    }
    
    public Anime updateAnime(Anime anime){
        if(anime == null) throw new CustomException("No se ingresaron datos.");
        animeR.getById(anime.getId()).orElseThrow(()-> new CustomException("No se encontró ningún anime con el id: "+anime.getId() ));
        return animeR.updateAnime(anime);
    }
    
    public void deleteByIdAnime(Integer id){
        if(id == null) throw new CustomException("El ud es nulo");
        animeR.getById(id).orElseThrow(()-> new CustomException("No se encontró ningún anime con el id: "+id));
        animeR.deleteByIdAnime(id);
    }
    
}
