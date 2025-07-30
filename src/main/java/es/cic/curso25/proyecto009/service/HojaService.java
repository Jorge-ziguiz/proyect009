package es.cic.curso25.proyecto009.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proyecto009.model.Hoja;
import es.cic.curso25.proyecto009.repository.HojaRepository;

@Service
@Transactional
public class HojaService {

    @Autowired
    private HojaRepository hojaRepository;


    public Hoja create(Hoja hoja) {
        return hojaRepository.save(hoja);
    }

    public Hoja getById(Long id) {
        Hoja hoja = hojaRepository.findById(id).orElse(null);
        return hoja;
    }

    public List<Hoja> getByAll() {
        List<Hoja> hojas = hojaRepository.findAll();
        return hojas;
    }

    public void update(Hoja rama) {
        hojaRepository.save(rama);
    }
}