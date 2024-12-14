package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.Rh;
import mx.sgahc.repository.pacientes.RhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RhServiceImpl implements RhService {
    private final RhRepository rhRepository;

    @Autowired
    public RhServiceImpl(RhRepository rhRepository) {
        this.rhRepository = rhRepository;
    }


    @Override
    public Rh getRhById(Integer id) {
        return rhRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rh> getAllRh() {
        return rhRepository.findAll();
    }
}
