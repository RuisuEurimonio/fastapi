/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import jakarta.transaction.Transactional;
import org.hibernate.grammars.hql.HqlParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ruisu's
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class AnimeControllerTest {
    
    @Autowired
        private MockMvc mockMvc;
    
	@Test
        @DisplayName("With the initial data this test should return the list length of 5 with status OK")
	void getAnime_GetAnimesAll_ReturnIsOk() throws Exception{
            mockMvc.perform(get("/api/animes/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(5));
        }
        
        @Test
        @DisplayName("Getted the initial value with id 4, returned the expected value with status accepted")
        void getAnime_GetInitialData_ReturnIsAccepted() throws Exception{
            String expected = "{\"id\":4, \"name\": \"Tokidoki Bosotto Russia-go de Dereru Tonari no Alya-san\",\"finished\":false}";
            
            mockMvc.perform(get("/api/animes/4"))
                    .andExpect(status().isAccepted())
                    .andExpect(content().json(expected));
        }
        
        @Test
        @Rollback
        @DisplayName("Created a new anime with the necessary data and verified it was returned as created")
        void createdAnime_CreateAnime_ReturnIsCreated() throws Exception{
            String newAnimeJson = "{\"name\": \"One piece\", \"finished\": \"true\"}";
            
            mockMvc.perform(post("/api/animes/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newAnimeJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("One piece"))
                    .andExpect(jsonPath("$.finished").value("true"))
                    .andExpect(jsonPath("$.id").value(6));
        }
        
        @Test
        @DisplayName("Tried to create a new anime, but the name field is missing; returned a bad request.")
        void createdAnime_CreateAnimeWhitoutName_ReturnIsBadRequest() throws Exception{
            String newAnimeJson = "{\"finished\": \"false\"}";
            
            mockMvc.perform(post("/api/animes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newAnimeJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("El nombre es necesario"));
        }
       
        @Test
        @DisplayName("Tried to create a new anime, but the finished field is missing, returned a bad request")
        void createdAnime_CreateAnimeWithoudFinished_ReturnIsBadRequest() throws Exception{
            String newAnimeJson = "{\"name\": \"One piece\"}";
            
            mockMvc.perform(post("/api/animes/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newAnimeJson))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.finished").value("El estado es necesario"));
                    
        }
        
        @Test
        @DisplayName("Tried to create a new anime, but the name field is too short, the validation failed and returned a bad request with an error message.")
        void createdAnime_CreateAnimeWithShortName_ReturnIsBadRequest() throws Exception{
            String newAnimeJson = "{\"id\":4, \"name\": \"To\",\"finished\":false}";
            
            mockMvc.perform(post("/api/animes/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newAnimeJson))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.name").value("El nombre debe estar entre 3 y 150 caracteres."));
        }
        
        @Test
        @DisplayName("Tried to create a new anime, but the name field is too long, the validation failed and returned a bad request with an error message.")
        void createdAnime_CreateAnimeWithLongerName_ReturnIsBadRequest() throws Exception{
            String newAnimeJson = "{\"id\":4, \"name\": \"Tokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-sanTokidoki Bosotto Russia-go de Dereru Tonari no Alya-san\",\"finished\":false}";
            
            mockMvc.perform(post("/api/animes/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newAnimeJson))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.name").value("El nombre debe estar entre 3 y 150 caracteres."));
        }
    
        @Test
        @DisplayName("Tried to create a new anime, but the image field is too short, the validation failed and returned a bad request with an error message")
        void createdAnime_CreateAnimeWithNotValidImage_ReturnIsBadRequest() throws Exception{
            String newAnimeJson = "{\"id\":4, \"name\": \"Tokidoki Bosotto Russia-go de Dereru Tonari no Alya-san\",\"finished\":false, \"image\": \"http\"}";
            
            mockMvc.perform(post("/api/animes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newAnimeJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.image").value("El link de la imagen no es valido."));
        }
        
        @Test
        @DisplayName("Update anime with id 4 with a new name, returned a created status")
        void updateAnime_UpdateAnimeRename_ReturnIsAccepted() throws Exception{
            String expected = "{\"id\":4, \"name\": \"Tokidoki Bosotto Russia-go\",\"finished\":false}";
            String modifyJson = "{\"id\":4,\"name\":\"Tokidoki Bosotto Russia-go\"}";
            
            mockMvc.perform(put("/api/animes/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(modifyJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(expected));
        }
        
        @Test
        @DisplayName("Update anime with id 4 change the finished state, returned a created status")
        void updateAnime_UpdateChangeTheFinished_ReturnIsAccepted() throws Exception{
            String expected = "{\"id\":4, \"name\": \"Tokidoki Bosotto Russia-go de Dereru Tonari no Alya-san\",\"finished\":true}";
            String modifyJson = "{\"id\":4,\"finished\":true}";
            
            mockMvc.perform(put("/api/animes/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modifyJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(expected));
        }
        
        @Test
        @DisplayName("Update anime with a new image, returned a created status")
        void updateAnime_changeImage_ReturnIsAccepted() throws Exception{
            String expected = "{\"id\":4, \"name\": \"Tokidoki Bosotto Russia-go de Dereru Tonari no Alya-san\",\"finished\":false, \"image\": \"http://localHost/img/12535\"}";
            String modifyJson = "{\"id\":4, \"image\": \"http://localHost/img/12535\"}";
            
            mockMvc.perform(put("/api/animes/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(modifyJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(expected));
            
        }
        
        @Test
        @DisplayName("Update anime with a new data in general, returned a created status.")
        void updateAnime_changeCompleteData_ReturnIsAccepted() throws Exception{
            String expected = "{\"id\":4, \"name\": \"Tonari no Alya-san\",\"finished\":true, \"image\": \"http://localHost/img/54321\"}";
            String modifyJson = "{\"id\":4, \"name\": \"Tonari no Alya-san\",\"finished\":true, \"image\": \"http://localHost/img/54321\"}";
            
            mockMvc.perform(put("/api/animes/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(modifyJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(expected));
                    
        }
        
        @Test
        @DisplayName("Update anime with a new data in general but without id, returned a bad request status.")
        void updateAnime_changeCompleteDataWithoutId_ReturnIsBadRequest() throws Exception{
            String modifyJson = "{\"name\": \"Tonari no Alya-san\",\"finished\":true, \"image\": \"http://localHost/img/54321\"}";
            
            mockMvc.perform(put("/api/animes/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(modifyJson))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("No se ingreso un id"));
                    
        }
        
        @Test
        @DisplayName("Update anime without data, returned a bad request status.")
        void updateAnime_WithoutData_ReturnIsBadRequest() throws Exception{
            mockMvc.perform(put("/api/animes/update")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Ha sucedido un error, vuelvelo a intentar"));
                    
        }
        
        @Test
        @DisplayName("Delete anime with id 1 returned a no content status")
        void deleteAnime_existData_returnNoContent() throws Exception{
            mockMvc.perform(delete("/api/animes/delete/1"))
                    .andExpect(status().isNoContent());
        }
        
        @Test
        @DisplayName("Deleting anime with ID 9999999 returned a bad request because the anime with ID 999999 does not exist.")
        void deleteAnime_notExistData_returnBadRequest() throws Exception{
            mockMvc.perform(delete("/api/animes/delete/999999"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("No se encontró ningún anime con el id: 999999"));
        }
}
