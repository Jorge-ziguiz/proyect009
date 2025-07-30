package es.cic.curso25.proyecto009.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyecto009.model.Arbol;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ArbolControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testCreate() throws Exception {

                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Long id = objectMapper.readValue(JsonArbolResponsePost, Arbol.class).getId();
                assertTrue(id > 0);

        }

        @Test
        void testDelete() throws Exception {
                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Long id = objectMapper.readValue(JsonArbolResponsePost, Arbol.class).getId();

                mockMvc.perform(delete("/arbol/" + id))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print());

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol/" + id))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                assertEquals("", JsonArbolResponseGet);

        }

        @Test
        void testGet() throws Exception {
                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Long id = objectMapper.readValue(JsonArbolResponsePost, Arbol.class).getId();

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol/" + id))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol ArbolResponseGet = objectMapper.readValue(JsonArbolResponseGet, Arbol.class);
                assertTrue(ArbolResponseGet.getId() > 0);
                assertEquals(arbol.getFamilia(), ArbolResponseGet.getFamilia());

        }

        @Test
        void testGetALL() throws Exception {
                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Long id = objectMapper.readValue(JsonArbolResponsePost, Arbol.class).getId();

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol"))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                List<Arbol> ArbolesResponseGet = objectMapper.readValue(JsonArbolResponseGet,
                                new TypeReference<List<Arbol>>() {
                                });
                assertTrue(ArbolesResponseGet.size() > 0);

        }

        @Test
        void testUpdate() throws Exception {

                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol arbolResultado = objectMapper.readValue(JsonArbolResponsePost, Arbol.class);
                arbolResultado.setFamilia("Nueva Familia");

                String JSONArbolUpdate = objectMapper.writeValueAsString(arbolResultado);

                mockMvc.perform(put("/arbol")
                                .contentType("application/json")
                                .content(JSONArbolUpdate))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn();

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol/" + arbolResultado.getId()))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol ArbolResponseGet = objectMapper.readValue(JsonArbolResponseGet, Arbol.class);
                assertEquals(ArbolResponseGet.getFamilia(), "Nueva Familia");
        }

        @BeforeEach
        private void deleteArbolIfExist() throws Exception {
                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol"))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                List<Arbol> ArbolesResponseGet = objectMapper.readValue(JsonArbolResponseGet,
                                new TypeReference<List<Arbol>>() {
                                });

                for (Arbol arbol : ArbolesResponseGet) {
                        if (arbol.getNombre().equals("Espeletia"))

                                mockMvc.perform(delete("/arbol/" + arbol.getId()))
                                                .andExpect(status().is2xxSuccessful())
                                                .andDo(print());
                }

        }
}
