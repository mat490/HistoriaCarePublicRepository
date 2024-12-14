package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.Rh;
import mx.sgahc.model.pacientes.dto.RhDTO;

import java.util.List;

public interface RhDTOService {
    RhDTO toDTO(Rh rh);
    Rh toEntity(RhDTO rhDTO);
    RhDTO getRhById(Integer id);
    List<RhDTO> getAllRh();
}
