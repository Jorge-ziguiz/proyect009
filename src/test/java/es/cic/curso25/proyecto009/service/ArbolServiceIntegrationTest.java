package es.cic.curso25.proyecto009.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyecto009.model.Arbol;

@SpringBootTest
public class ArbolServiceIntegrationTest {

    @Autowired
    private ArbolService arbolService;

    @Test
    void testCreate() {
        Arbol arbol = new Arbol();
        arbol.setNombre("Espeletia");
        arbol.setFamilia("Asteraceae");
        arbol.setOrden("Asterales");

        Long id = arbolService.create(arbol).getId();

        Arbol ArbolGet = arbolService.getById(id);

        assertEquals(id, ArbolGet.getId());
    }

    @Test
    void testDeleteById() {
        Arbol arbol = new Arbol();
        arbol.setNombre("Espeletia");
        arbol.setFamilia("Asteraceae");
        arbol.setOrden("Asterales");

        Long id = arbolService.create(arbol).getId();

        arbolService.deleteById(id);

        Arbol ArbolGet = arbolService.getById(id);

        assertEquals(ArbolGet, null);

    }

    @Test
    void testGetByAll() {
        Arbol arbol = new Arbol();
        arbol.setNombre("Espeletia");
        arbol.setFamilia("Asteraceae");
        arbol.setOrden("Asterales");

        arbolService.create(arbol).getId();

        List<Arbol> arboles = arbolService.getByAll();
        assertTrue(arboles.size() >= 1);

    }

    @Test
    void testGetById() {
        Arbol arbol = new Arbol();
        arbol.setNombre("Espeletia");
        arbol.setFamilia("Asteraceae");
        arbol.setOrden("Asterales");

        Long id = arbolService.create(arbol).getId();

        Arbol ArbolGet = arbolService.getById(id);

        assertEquals(id, ArbolGet.getId());
    }

    @Test
    void testUpdate() {
        Arbol arbol = new Arbol();
        arbol.setNombre("Espeletia");
        arbol.setFamilia("Asteraceae");
        arbol.setOrden("Asterales");

        Long id = arbolService.create(arbol).getId();

        Arbol ArbolGet = arbolService.getById(id);
        ArbolGet.setNombre("nuevo nombre");

        arbolService.update(ArbolGet);

        assertEquals(arbolService.getById(id).getNombre(), ArbolGet.getNombre());

    }

    @BeforeEach
    private void deleteIfExist() {
        List<Arbol> arboles = arbolService.getByAll();

        arboles.forEach(arbol -> {
            if (arbol.getNombre().equals("Espeletia"))

                arbolService.deleteById(arbol.getId());
        });

    }
}
