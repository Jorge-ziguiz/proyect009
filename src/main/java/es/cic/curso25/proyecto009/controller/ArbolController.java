package es.cic.curso25.proyecto009.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private RamaService ramaService;

    @Autowired
    private HojaService hojaService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Arbol create( @RequestBody Arbol arbol) {
        return arbolService.create(arbol);
    }

    
    @GetMapping(path = "/{id}")
    public Arbol get(long id){
        return arbolService.getById(Long.valueOf(id));
    }


    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Arbol update(@RequestBody Arbol arbol) {
        return arbolService.create(arbol);
    }

}
