package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.dto.AntecedenteFamiliarDTORequest;
import mx.sgahc.model.pacientes.dto.AntecedenteFamiliarDTOResponse;
import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AntecedenteFamiliarDTOService {

    AntecedenteFamiliarDTOResponse toDTORespomse(AntecedenteFamiliar antecedenteFamiliar);
    AntecedenteFamiliar toEntity(AntecedenteFamiliarDTORequest antecedenteFamiliarDTO);
    Page<AntecedenteFamiliarDTOResponse> findAntecedentesByPacienteId(Integer id, Pageable pageable);
    void saveAntecedenteFamiliar(AntecedenteFamiliarDTORequest antecedenteFamiliar);

}
