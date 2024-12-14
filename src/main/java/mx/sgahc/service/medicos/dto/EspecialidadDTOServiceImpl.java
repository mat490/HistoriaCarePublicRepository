package mx.sgahc.service.medicos.dto;

import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.dto.EspecialidadDTO;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.EspecialidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadDTOServiceImpl implements EspecialidadDTOService{
    private final EspecialidadService especialidadService;
    private final EspecialidadServiceImpl especialidadServiceImpl;

    @Autowired
    public EspecialidadDTOServiceImpl(EspecialidadService especialidadService, EspecialidadServiceImpl especialidadServiceImpl) {
        this.especialidadService = especialidadService;
        this.especialidadServiceImpl = especialidadServiceImpl;
    }

    @Override
    public EspecialidadDTO toDTO(Especialidad especialidad) {
        if (especialidad != null) {
            EspecialidadDTO especialidadDTO = new EspecialidadDTO();
            if (especialidad.getId() != null)
                especialidadDTO.setId(especialidad.getId());
            if (especialidad.getEspecialidad() != null)
                especialidadDTO.setEspecialidad(especialidad.getEspecialidad());
            return especialidadDTO;
        }
        return null;
    }

    @Override
    public Especialidad toEntity(EspecialidadDTO especialidad) {
        if (especialidad != null) {
            Especialidad especialidadDTO = new Especialidad();
            if (especialidad.getId() != null)
                especialidadDTO.setId(especialidad.getId());
            if (especialidad.getEspecialidad() != null)
                especialidadDTO.setEspecialidad(especialidad.getEspecialidad());
            return especialidadDTO;
        }
        return null;
    }

    @Override
    public List<EspecialidadDTO> getAllEspecialidades() {
        return especialidadServiceImpl.getAllEspecialidades().stream().map(this::toDTO).toList();
    }

    @Override
    public EspecialidadDTO getEspecialidadDTOById(int id) {
        return this.toDTO(especialidadService.getEspecialidadById(id));
    }
}
