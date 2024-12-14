package mx.sgahc.service.medicos.dto;

import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.dto.EspecialidadDTO;

import java.util.List;

public interface EspecialidadDTOService {
    EspecialidadDTO toDTO(Especialidad especialidad);
    Especialidad toEntity(EspecialidadDTO especialidad);
    List<EspecialidadDTO> getAllEspecialidades();
    EspecialidadDTO getEspecialidadDTOById(int id);
}
