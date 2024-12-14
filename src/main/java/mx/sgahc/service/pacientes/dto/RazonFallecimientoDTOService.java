package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.dto.RazonFallecimientoDTOResponse;
import mx.sgahc.model.pacientes.familiares.RazonFallecimiento;

import java.util.List;

public interface RazonFallecimientoDTOService {
    RazonFallecimientoDTOResponse toDTO(RazonFallecimiento razonFallecimiento);
    RazonFallecimiento toEntity(RazonFallecimientoDTOResponse razonFallecimiento);
    List<RazonFallecimientoDTOResponse> getRazonesFallecimientos();
}
