package mx.sgahc.service.tratamientos.dto;

import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.enfermedades.dto.EnfermedadDTOResponse;

import java.util.List;

public interface EnfermedadDTOService {
    EnfermedadDTOResponse toDto(Enfermedad enfermedad);
    List<EnfermedadDTOResponse> getEnfermedades();
}
