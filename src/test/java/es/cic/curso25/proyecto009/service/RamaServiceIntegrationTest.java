package es.cic.curso25.proyecto009.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyecto009.model.Rama;

@SpringBootTest
public class RamaServiceIntegrationTest {

    @Autowired
    private RamaService ramaService;

    @Test
    void testCreate() {

        Rama rama = new Rama();
        rama.setColor("cafe");
        rama.setForma("Triangular");
        rama.setLongitudEnMetros(3.5);

        Long id = ramaService.create(rama).getId();

        Rama resultado = ramaService.getById(id);

        assertEquals(rama.getColor(), resultado.getColor());

    }

    @Test
    void testGetByAll() {

    }

    @Test
    void testGetById() {
        Rama rama = new Rama();
        rama.setColor("cafe");
        rama.setForma("Triangular");
        rama.setLongitudEnMetros(3.5);

        Long id = ramaService.create(rama).getId();

        Rama resultado = ramaService.getById(id);

        assertEquals(rama.getColor(), resultado.getColor());

    }

    @Test
    void testUpdate() {
        Rama rama = new Rama();
        rama.setColor("cafe");
        rama.setForma("Triangular");
        rama.setLongitudEnMetros(3.5);

        Long id = ramaService.create(rama).getId();

        Rama resultado = ramaService.getById(id);
        resultado.setColor("nuevo color");
        ramaService.update(resultado);

        Rama resultadoActualizado = ramaService.getById(id);

        assertEquals(resultadoActualizado.getColor(), "nuevo color");

    }

    //@BeforeEach
    private void deleteIfExist() {
        List<Rama> ramas = ramaService.getByAll();

        if (ramas == null && ramas.isEmpty()) {
            return;
        }

        ramas.forEach(rama -> {
            if (rama != null && rama.getForma() != null && rama.getForma().equals("Triangular"))
                ramaService.deleteById(rama.getId());
        });
    }
}
