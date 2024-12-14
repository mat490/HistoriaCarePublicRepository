package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.EstadoCivil;
import mx.sgahc.model.pacientes.dto.EstadoCivilDTO;

import java.util.List;

public interface EstadoCivilDTOService {
    EstadoCivil toEntity(EstadoCivilDTO estadoCivilDTO);
    EstadoCivilDTO toDTO(EstadoCivil estadoCivil);
    EstadoCivilDTO getEstadoCivilById(Integer id);
    List<EstadoCivilDTO> getAllEstadoCivil();
}
