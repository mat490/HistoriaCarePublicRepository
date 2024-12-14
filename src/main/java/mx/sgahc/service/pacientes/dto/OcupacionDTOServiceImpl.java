package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.Ocupacion;
import mx.sgahc.model.pacientes.dto.OcupacionDTO;
import mx.sgahc.service.pacientes.OcupacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcupacionDTOServiceImpl implements OcupacionDTOService {
    private final OcupacionService ocupacionService;

    @Autowired
    public OcupacionDTOServiceImpl(OcupacionService ocupacionService) {
        this.ocupacionService = ocupacionService;
    }

    @Override
    public OcupacionDTO toDTO(Ocupacion ocupacion) {
        if (ocupacion != null) {
            OcupacionDTO ocupacionDTO = new OcupacionDTO();
            if (ocupacion.getId() != null)
                ocupacionDTO.setId(ocupacion.getId());
            if (ocupacion.getOcupacion() != null)
                ocupacionDTO.setOcupacion(ocupacion.getOcupacion());
            return ocupacionDTO;
        }
        return null;
    }

    @Override
    public Ocupacion toEntity(OcupacionDTO ocupacionDTO) {
        if (ocupacionDTO != null) {
            Ocupacion ocupacion = new Ocupacion();
            if (ocupacionDTO.getId() != null)
                ocupacion.setId(ocupacionDTO.getId());
            if (ocupacionDTO.getOcupacion() != null)
                ocupacion.setOcupacion(ocupacionDTO.getOcupacion());
            return ocupacion;
        }
        return null;
    }

    @Override
    public OcupacionDTO getOcupacionById(Integer id) {
        return this.toDTO(ocupacionService.getOcupacionById(id));
    }

    @Override
    public List<OcupacionDTO> getOcupaciones() {
        return ocupacionService.getOcupaciones().stream().map(this::toDTO).toList();
    }
}
