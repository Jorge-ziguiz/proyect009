package es.cic.curso25.proyecto009.uc;

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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyecto009.model.Arbol;
import es.cic.curso25.proyecto009.model.Hoja;
import es.cic.curso25.proyecto009.model.Rama;

@SpringBootTest
@AutoConfigureMockMvc
public class ArbolTieneRamasyRamasTienenHojasIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void createArbolDeRamasYHojas() throws Exception {

                Hoja hoja = new Hoja();
                hoja.setColor("verde");
                hoja.setTipo("aovada");

                Hoja hoja2 = new Hoja();
                hoja.setColor("verde-oscuro");
                hoja.setTipo("aovada y dentada");

                List<Hoja> hojas = new ArrayList<>();
                hojas.add(hoja);
                hojas.add(hoja2);

                Rama rama = new Rama();
                rama.setColor("cafe");
                rama.setForma("Triangular");
                rama.setLongitudEnMetros(3.5);
                rama.setHojas(hojas);

                Rama rama2 = new Rama();
                rama2.setColor("cafe-oscuro");
                rama2.setForma("Triangular");
                rama2.setLongitudEnMetros(2.3);

                List<Rama> ramas = new ArrayList<>();
                ramas.add(rama);
                ramas.add(rama2);

                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");
                arbol.setRamas(ramas);

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol ArbolResponsePost = objectMapper.readValue(JsonArbolResponsePost, Arbol.class);

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol/" + ArbolResponsePost.getId()))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol ArbolResponseGet = objectMapper.readValue(JsonArbolResponseGet, Arbol.class);

                assertTrue(ArbolResponseGet.getId() > 0);
                assertTrue(ArbolResponseGet.getRamas().size() >= 2);

                ArbolResponseGet.getRamas().stream().forEach(r -> {
                        if (r.getHojas() != null && !r.getHojas().isEmpty()) {
                                assertTrue(r.getHojas().size() >= 2);
                        }
                });
        }

        @Test
        public void updateInCascade() throws Exception {

                Hoja hoja = new Hoja();
                hoja.setColor("verde");
                hoja.setTipo("aovada");

                Hoja hoja2 = new Hoja();
                hoja.setColor("verde-oscuro");
                hoja.setTipo("aovada y dentada");

                List<Hoja> hojas = new ArrayList<>();
                hojas.add(hoja);
                hojas.add(hoja2);

                Rama rama = new Rama();
                rama.setColor("cafe");
                rama.setForma("Triangular");
                rama.setLongitudEnMetros(3.5);
                rama.setHojas(hojas);

                Rama rama2 = new Rama();
                rama2.setColor("cafe-oscuro");
                rama2.setForma("Triangular");
                rama2.setLongitudEnMetros(2.3);

                List<Rama> ramas = new ArrayList<>();
                ramas.add(rama);
                ramas.add(rama2);

                Arbol arbol = new Arbol();
                arbol.setNombre("Pinus");
                arbol.setFamilia("Pinaceae");
                arbol.setOrden("pinales");
                arbol.setRamas(ramas);

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol ArbolResponsePost = objectMapper.readValue(JsonArbolResponsePost, Arbol.class);

                List<Hoja> getHojas = ArbolResponsePost.getRamas().get(0).getHojas();
                List<Hoja> updateHojas = new ArrayList<>();
                getHojas.forEach(h -> {
                        if (h.getTipo() != "aovada") {
                                updateHojas.add(h);
                        }
                });
                ArbolResponsePost.getRamas().get(0).setHojas(updateHojas);

                Rama updateRama = ArbolResponsePost.getRamas().get(0);
                updateRama.setColor("verde claro");
                updateRama.setLongitudEnMetros(1.0);

                ArbolResponsePost.getRamas().forEach(r -> {
                        if (r.getId().equals(updateRama.getId())) {
                                r.setColor(updateRama.getColor());
                                r.setLongitudEnMetros(updateRama.getLongitudEnMetros());
                        }
                });

                String JsonArbolUpdate = objectMapper.writeValueAsString(ArbolResponsePost);

                mockMvc.perform(put("/arbol")
                                .contentType("application/json")
                                .content(JsonArbolUpdate))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print());

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol/" + ArbolResponsePost.getId()))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol arbolResultado = objectMapper.readValue(JsonArbolResponseGet, Arbol.class);

                assertEquals(arbolResultado.getRamas().get(0).getColor(), "verde claro");

                List<Hoja> hojasResultado = ArbolResponsePost.getRamas().get(0).getHojas();
                hojasResultado.forEach(h -> {
                        assertTrue(h.getTipo() != "aovada");

                });
        }

        @Test
        public void deleteINCascade() throws Exception {

                Hoja hoja = new Hoja();
                hoja.setColor("verde");
                hoja.setTipo("aovada");

                Hoja hoja2 = new Hoja();
                hoja.setColor("verde-oscuro");
                hoja.setTipo("aovada y dentada");

                List<Hoja> hojas = new ArrayList<>();
                hojas.add(hoja);
                hojas.add(hoja2);

                Rama rama = new Rama();
                rama.setColor("cafe");
                rama.setForma("Triangular");
                rama.setLongitudEnMetros(3.5);
                rama.setHojas(hojas);

                Rama rama2 = new Rama();
                rama2.setColor("cafe-oscuro");
                rama2.setForma("Triangular");
                rama2.setLongitudEnMetros(2.3);

                List<Rama> ramas = new ArrayList<>();
                ramas.add(rama);
                ramas.add(rama2);

                Arbol arbol = new Arbol();
                arbol.setNombre("Espeletia");
                arbol.setFamilia("Asteraceae");
                arbol.setOrden("Asterales");
                arbol.setRamas(ramas);

                String JsonArbolResquestPost = objectMapper.writeValueAsString(arbol);

                String JsonArbolResponsePost = mockMvc
                                .perform(post("/arbol/ramas")
                                                .contentType("application/json")
                                                .content(JsonArbolResquestPost))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Arbol ArbolResponsePost = objectMapper.readValue(JsonArbolResponsePost, Arbol.class);

                mockMvc.perform(delete("/arbol/" + ArbolResponsePost.getId()))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print());

                String JsonArbolResponseGet = mockMvc
                                .perform(get("/arbol/" + ArbolResponsePost.getId()))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                assertTrue(JsonArbolResponseGet.isEmpty());
        }

}
