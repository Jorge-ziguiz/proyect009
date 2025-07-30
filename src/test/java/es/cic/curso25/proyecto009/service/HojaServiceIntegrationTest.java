package es.cic.curso25.proyecto009.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyecto009.model.Hoja;

@SpringBootTest
public class HojaServiceIntegrationTest {

    @Autowired
    private HojaService hojaService;

    @Test
    void testCreate() {
        Hoja hoja = new Hoja();
        hoja.setColor("verde");
        hoja.setTipo("aovada");

        Long id = hojaService.create(hoja).getId();

        Hoja Hojaget = hojaService.getById(id);

        assertEquals(id, Hojaget.getId());
    }

    @Test
    void testDeleteById() {
        Hoja hoja = new Hoja();
        hoja.setColor("verde");
        hoja.setTipo("aovada");

        Long id = hojaService.create(hoja).getId();

        hojaService.deleteById(id);

        Hoja Hojaget = hojaService.getById(id);

        assertEquals(Hojaget, null);

    }

    @Test
    void testGetByAll() {
        Hoja hoja = new Hoja();
        hoja.setColor("verde");
        hoja.setTipo("aovada");

        hojaService.create(hoja).getId();

        List<Hoja> hojas = hojaService.getByAll();
        assertTrue(hojas.size() >= 1);

    }

    @Test
    void testGetById() {
        Hoja hoja = new Hoja();
        hoja.setColor("verde");
        hoja.setTipo("aovada");

        Long id = hojaService.create(hoja).getId();

        Hoja Hojaget = hojaService.getById(id);

        assertEquals(id, Hojaget.getId());
    }

    @Test
    void testUpdate() {
        Hoja hoja = new Hoja();
        hoja.setColor("verde");
        hoja.setTipo("aovada");

        Long id = hojaService.create(hoja).getId();

        Hoja HojaGet = hojaService.getById(id);
        HojaGet.setTipo("nuevo tipo");

        hojaService.update(HojaGet);

        assertEquals(hojaService.getById(id).getTipo(), HojaGet.getTipo());

    }

    @BeforeEach
    private void deleteIfExist() {
        List<Hoja> Hojas = hojaService.getByAll();

        if (Hojas == null) {
            return;
        }

        Hojas.forEach(hoja -> {
            if (hoja.getTipo().equals("aovada"))
                hojaService.deleteById(hoja.getId());
        });

    }
}
