package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.Combe;
import mx.sgahc.model.pacientes.dto.CombeDTO;

import java.util.List;

public interface CombeDTOService {
    CombeDTO toDto(Combe combe);
    Combe toEntity(CombeDTO combeDTO);
    CombeDTO getCombeById(Integer id);
    List<CombeDTO> getAllCombe();
}
