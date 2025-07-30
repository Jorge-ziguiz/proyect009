package es.cic.curso25.proyecto009.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proyecto009.exceptionHandler.SecureNoAllowNewID;
import es.cic.curso25.proyecto009.model.Arbol;
import es.cic.curso25.proyecto009.service.ArbolService;
import es.cic.curso25.proyecto009.service.HojaService;
import es.cic.curso25.proyecto009.service.RamaService;

@RestController
@RequestMapping(path = "arbol")
public class ArbolController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArbolController.class);

    @Autowired
    private ArbolService arbolService;



    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Arbol create(@RequestBody Arbol arbol) {
        if (arbol.getId() != null && arbol.getId() != 0) {
            LOGGER.error("no se puede crear una entidad con id");
            throw new SecureNoAllowNewID("no se puede crear una entidad con id");
        }
        arbol.setRamas(null);
        return arbolService.create(arbol);
    }

    @PostMapping(path = "ramas")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Arbol createWithRamas(@RequestBody Arbol arbol) {
        if (arbol.getId() != null && arbol.getId() != 0) {
            LOGGER.error("no se puede crear una entidad con id");
            throw new SecureNoAllowNewID("no se puede crear una entidad con id");
        }
        return arbolService.create(arbol);
    }

    @GetMapping(path = "/{id}")
    public Arbol get(@PathVariable long id) {
        return arbolService.getById(Long.valueOf(id));
    }

    @GetMapping()
    public List<Arbol> get() {
        return arbolService.getByAll();
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        arbolService.deleteById(Long.valueOf(id));
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Arbol update(@RequestBody Arbol arbol) {
        if ((arbolService.getById(arbol.getId())) == null) {
            LOGGER.error("no se actualizar una entidad que no este en la base de datos id");
            throw new SecureNoAllowNewID("no se puede crear una entidad con id");
        }
        return arbolService.create(arbol);
    }

}
