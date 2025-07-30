package es.cic.curso25.proyecto009.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proyecto009.model.Rama;
import es.cic.curso25.proyecto009.repository.RamaRepository;

@Service
@Transactional
public class RamaService {
    @Autowired
    private RamaRepository ramaRepository;

    public Rama create(Rama rama) {
        return ramaRepository.save(rama);
    }

    public Rama getById(Long id) {
        Rama rama = ramaRepository.findById(id).orElse(null);
        return rama;
    }

    public List<Rama> getByAll() {
        List<Rama> ramas = ramaRepository.findAll();
        return ramas;
    }

    public void update(Rama rama) {
        ramaRepository.save(rama);
    }
    
    public void deleteById(Long id) {
        ramaRepository.deleteById(id);
    }
}
