package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.EstadoCivil;
import mx.sgahc.model.pacientes.dto.EstadoCivilDTO;
import mx.sgahc.service.pacientes.EstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilDTOServiceImpl implements EstadoCivilDTOService {
    private final EstadoCivilService estadoCivilService;

    @Autowired
    public EstadoCivilDTOServiceImpl(EstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    @Override
    public EstadoCivil toEntity(EstadoCivilDTO estadoCivilDTO) {
        EstadoCivil entity = new EstadoCivil();
        if (estadoCivilDTO != null) {
            if (estadoCivilDTO.getId() != null){
                entity = estadoCivilService.getEstadoCivilById(estadoCivilDTO.getId());
                return entity;
            }
            if (estadoCivilDTO.getEstadoCivil() != null)
                entity.setEstadoCivil(estadoCivilDTO.getEstadoCivil());
            return entity;

        }
        return null;
    }

    @Override
    public EstadoCivilDTO toDTO(EstadoCivil estadoCivil) {
        if (estadoCivil != null) {
            EstadoCivilDTO dto = new EstadoCivilDTO();
            if (estadoCivil.getId() != null)
                dto.setId(estadoCivil.getId());
            if (estadoCivil.getEstadoCivil() != null)
                dto.setEstadoCivil(estadoCivil.getEstadoCivil());
            return dto;
        }
        return null;
    }

    @Override
    public EstadoCivilDTO getEstadoCivilById(Integer id) {
        return this.toDTO(estadoCivilService.getEstadoCivilById(id));
    }

    @Override
    public List<EstadoCivilDTO> getAllEstadoCivil() {
        return estadoCivilService.getAllEstadoCivil().stream().map(this::toDTO).toList();
    }
}
