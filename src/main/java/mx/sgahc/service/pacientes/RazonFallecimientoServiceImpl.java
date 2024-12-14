package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.familiares.RazonFallecimiento;
import mx.sgahc.repository.pacientes.familiares.RazonFallecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazonFallecimientoServiceImpl implements RazonFallecimientoService {
    private final RazonFallecimientoRepository razonFallecimientoRepository;

    @Autowired
    public RazonFallecimientoServiceImpl(RazonFallecimientoRepository razonFallecimientoRepository) {
        this.razonFallecimientoRepository = razonFallecimientoRepository;
    }

    @Override
    public List<RazonFallecimiento> getRazonesFallecimientos() {
        return razonFallecimientoRepository.findAll();
    }
}
