package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.GrupoSanguineo;
import mx.sgahc.model.pacientes.dto.GrupoSanguineoDTO;

import java.util.List;

public interface GrupoSanguineoDTOService {
    GrupoSanguineoDTO toDto(GrupoSanguineo grupoSanguineo);
    GrupoSanguineo toEntity(GrupoSanguineoDTO grupoSanguineoDTO);
    GrupoSanguineoDTO getGrupoSanguineoById(Integer id);
    List<GrupoSanguineoDTO> getAllGrupoSanguineo();
}
