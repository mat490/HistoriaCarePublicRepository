package mx.sgahc.service.tratamientos.dto;

import mx.sgahc.model.enfermedades.Medicamento;
import mx.sgahc.model.enfermedades.dto.MedicamentoDTOResponse;

import java.util.List;

public interface MedicamentoDTOService {
    MedicamentoDTOResponse toDTO(Medicamento medicamento);
    Medicamento toEntity(MedicamentoDTOResponse medicamentoDTOResponse);
    List<MedicamentoDTOResponse> getAllMedicamentos();
    MedicamentoDTOResponse getMedicamentoById(Integer id);
}
