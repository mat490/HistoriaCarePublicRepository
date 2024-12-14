package mx.sgahc.service.tratamientos.dto;

import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.enfermedades.dto.EnfermedadDTOResponse;
import mx.sgahc.service.tratamientos.EnfermedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfermedadDTOServiceImpl implements EnfermedadDTOService {
    private final EnfermedadService enfermedadService;

    @Autowired
    public EnfermedadDTOServiceImpl(EnfermedadService enfermedadService) {
        this.enfermedadService = enfermedadService;
    }

    @Override
    public EnfermedadDTOResponse toDto(Enfermedad enfermedad) {
        if (enfermedad != null) {
            EnfermedadDTOResponse dto = new EnfermedadDTOResponse();
            if (enfermedad.getId() != null)
                dto.setId(enfermedad.getId());
            if (enfermedad.getEnfermedad() != null)
                dto.setEnfermedad(enfermedad.getEnfermedad());
            if (enfermedad.getDescripcion() != null)
                dto.setDescripcion(enfermedad.getDescripcion());
            return dto;
        }
        return null;
    }

    @Override
    public List<EnfermedadDTOResponse> getEnfermedades() {
        return enfermedadService.getEnfermedades().stream().map(this::toDto).toList();
    }
}
