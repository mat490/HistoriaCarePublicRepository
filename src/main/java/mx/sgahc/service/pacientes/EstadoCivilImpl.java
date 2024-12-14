package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.EstadoCivil;
import mx.sgahc.repository.pacientes.EstadoCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilImpl implements EstadoCivilService{
    private final EstadoCivilRepository estadoCivilRepository;

    @Autowired
    public EstadoCivilImpl(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    @Override
    public EstadoCivil getEstadoCivilById(Integer id) {
        return estadoCivilRepository.findById(id).orElse(null);
    }

    @Override
    public List<EstadoCivil> getAllEstadoCivil() {
        return estadoCivilRepository.findAll();
    }
}
