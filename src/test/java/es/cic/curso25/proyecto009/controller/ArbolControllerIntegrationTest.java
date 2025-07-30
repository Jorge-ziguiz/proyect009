package es.cic.curso25.proyecto009.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyecto009.model.Arbol;
import es.cic.curso25.proyecto009.model.Hoja;
import es.cic.curso25.proyecto009.model.Rama;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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



        mockMvc.perform(post("arbol"));

    }

    @Test
    void testDelete() throws Exception {

    }

    @Test
    void testGet() throws Exception{

    }

    @Test
    void testGet2() throws Exception {

    }

    @Test
    void testUpdate() throws Exception {

    }
}
