package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.Ocupacion;
import mx.sgahc.model.pacientes.dto.OcupacionDTO;

import java.util.List;

public interface OcupacionDTOService {
    OcupacionDTO toDTO(Ocupacion ocupacion);
    Ocupacion toEntity(OcupacionDTO ocupacionDTO);
    OcupacionDTO getOcupacionById(Integer id);
    List<OcupacionDTO> getOcupaciones();
}
