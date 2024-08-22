/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import jakarta.transaction.Transactional;
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
	void shouldGetAnimesAllReturnIsOk() throws Exception{
            mockMvc.perform(get("/api/animes/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(5));
        }
        
        @Test
        @Rollback
        void shouldCreateAnimeReturnIsOk() throws Exception{
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
        void shouldCreateAnimeReturnIsNotOk() throws Exception{
            String newAnimeJson = "{\"finished\": \"false\"}";
            
            mockMvc.perform(post("/api/animes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newAnimeJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("El nombre es necesario"));
                    
        }
    
}
