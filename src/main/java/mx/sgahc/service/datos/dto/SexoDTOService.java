package mx.sgahc.service.datos.dto;

import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.datos.dto.SexoDTO;

import java.util.List;

public interface SexoDTOService {
    SexoDTO toDto(Sexo sexo);
    Sexo toEntity(SexoDTO sexoDTO);
    List<SexoDTO> getSexos();
}
