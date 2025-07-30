package es.cic.curso25.proyecto009.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proyecto009.model.Arbol;

import es.cic.curso25.proyecto009.repository.ArbolRepository;

@Service
@Transactional
public class ArbolService {

    @Autowired
    private ArbolRepository arbolRepository;

    public Arbol create(Arbol arbol) {
        return arbolRepository.save(arbol);
    }

    public Arbol getById(Long id) {
        Arbol arbol = arbolRepository.findById(id).orElse(null);
        return arbol;
    }

    public void deleteById(Long id) {
        arbolRepository.deleteById(id);
    }

    public List<Arbol> getByAll() {
        List<Arbol> arboles = arbolRepository.findAll();
        return arboles;
    }

    public void update(Arbol arbol) {
        arbolRepository.save(arbol);

        // List<Rama> ramas = new ArrayList<>();
        // List<Hoja> hojas = new ArrayList<>();
        // if (arbol.getRamas() != null && !(ramas = arbol.getRamas()).isEmpty()) {

        // for (Rama rama : ramas) {
        // if (rama.getHojas() != null && !(hojas = rama.getHojas()).isEmpty())

        // for (Hoja hoja : hojas) {
        // if (hoja.getTipo() != null) {
        // continue;
        // }
        // if (hoja.getColor() == null) {
        // hojaService.deleteById(hoja.getId());
        // }
        // }

        // if (rama.getForma() != null) {
        // continue;
        // }
        // if (rama.getColor() == null && rama.getLongitudEnMetros() == null) {
        // ramaService.deleteById(rama.getId());
        // }
        // }
        // }
    }

}
