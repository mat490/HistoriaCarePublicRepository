package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.Ocupacion;
import mx.sgahc.repository.pacientes.OcupacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcupacionServiceImpl implements OcupacionService {
    private final OcupacionRepository ocupacionRepository;

    @Autowired
    public OcupacionServiceImpl(OcupacionRepository ocupacionRepository) {
        this.ocupacionRepository = ocupacionRepository;
    }

    @Override
    public Ocupacion getOcupacionById(Integer id) {
        return ocupacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ocupacion> getOcupaciones() {
        return ocupacionRepository.findAll();
    }
}
