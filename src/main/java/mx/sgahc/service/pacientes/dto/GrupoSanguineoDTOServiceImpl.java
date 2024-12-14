package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.GrupoSanguineo;
import mx.sgahc.model.pacientes.dto.GrupoSanguineoDTO;
import mx.sgahc.service.pacientes.GrupoSanguineoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoSanguineoDTOServiceImpl implements GrupoSanguineoDTOService{
    private final GrupoSanguineoService grupoSanguineoService;

    @Autowired
    public GrupoSanguineoDTOServiceImpl(GrupoSanguineoService grupoSanguineoService) {
        this.grupoSanguineoService = grupoSanguineoService;
    }

    @Override
    public GrupoSanguineoDTO toDto(GrupoSanguineo grupoSanguineo) {
        if (grupoSanguineo != null){
            GrupoSanguineoDTO dto = new GrupoSanguineoDTO();
            if (grupoSanguineo.getId() != null)
                dto.setId(grupoSanguineo.getId());
            if (grupoSanguineo.getGrupoSanguineo() != null)
                dto.setGrupoSanguineo(grupoSanguineo.getGrupoSanguineo());
            return dto;

        }
        return null;
    }

    @Override
    public GrupoSanguineo toEntity(GrupoSanguineoDTO grupoSanguineoDTO) {
        if (grupoSanguineoDTO != null) {
            GrupoSanguineo entity = new GrupoSanguineo();
            if (grupoSanguineoDTO.getId() != null)
                entity.setId(grupoSanguineoDTO.getId());
            if (grupoSanguineoDTO.getGrupoSanguineo() != null)
                entity.setGrupoSanguineo(grupoSanguineoDTO.getGrupoSanguineo());
            return entity;

        }
        return null;
    }

    @Override
    public GrupoSanguineoDTO getGrupoSanguineoById(Integer id) {
        return this.toDto(grupoSanguineoService.getGrupoSanguineoById(id));
    }

    @Override
    public List<GrupoSanguineoDTO> getAllGrupoSanguineo() {
        return grupoSanguineoService.getAllGrupoSanguineo().stream().map(this::toDto).toList();
    }
}
