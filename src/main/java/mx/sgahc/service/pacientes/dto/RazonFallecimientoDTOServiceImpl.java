package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.dto.RazonFallecimientoDTOResponse;
import mx.sgahc.model.pacientes.familiares.RazonFallecimiento;
import mx.sgahc.repository.pacientes.familiares.RazonFallecimientoRepository;
import mx.sgahc.service.pacientes.RazonFallecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazonFallecimientoDTOServiceImpl implements RazonFallecimientoDTOService{
    private final RazonFallecimientoService razonFallecimientoService;
    private final RazonFallecimientoRepository razonFallecimientoRepository;

    @Autowired
    public RazonFallecimientoDTOServiceImpl(RazonFallecimientoService razonFallecimientoService, RazonFallecimientoRepository razonFallecimientoRepository) {
        this.razonFallecimientoService = razonFallecimientoService;
        this.razonFallecimientoRepository = razonFallecimientoRepository;
    }

    @Override
    public RazonFallecimientoDTOResponse toDTO(RazonFallecimiento razonFallecimiento) {
        if (razonFallecimiento != null) {
            RazonFallecimientoDTOResponse dtoResponse = new RazonFallecimientoDTOResponse();
                dtoResponse = new RazonFallecimientoDTOResponse();
            if (razonFallecimiento.getId() != null)
                dtoResponse.setId(razonFallecimiento.getId());
            if (razonFallecimiento.getRazon() != null)
                dtoResponse.setRazon(razonFallecimiento.getRazon());
            return dtoResponse;
        }

        return null;
    }

    @Override
    public RazonFallecimiento toEntity(RazonFallecimientoDTOResponse razonFallecimiento) {
        if (razonFallecimiento != null) {
            RazonFallecimiento rf = new RazonFallecimiento();
            if (razonFallecimiento.getId() != null)
                rf.setId(razonFallecimiento.getId());
            if (razonFallecimiento.getRazon() != null)
                rf.setRazon(razonFallecimiento.getRazon());
            return rf;
        }
        return null;
    }

    @Override
    public List<RazonFallecimientoDTOResponse> getRazonesFallecimientos() {
        return razonFallecimientoService.getRazonesFallecimientos().stream().map(this::toDTO).toList();
    }
}
