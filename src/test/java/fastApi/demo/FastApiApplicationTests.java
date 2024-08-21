package fastApi.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class FastApiApplicationTests {

        @Autowired
        private MockMvc mockMvc;
    
	@Test
	void shouldGetAnimesAllReturnIsOk() throws Exception{
            mockMvc.perform(get("/api/animes/all"))
                    .andExpect(status().isOk());
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
                    .andExpect(jsonPath("$.finished").value("true"));
        }

}
