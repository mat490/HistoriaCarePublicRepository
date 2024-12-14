package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.AntecedentePatologico;
import mx.sgahc.model.pacientes.dto.AntecedentPatologicoDTOResponse;
import mx.sgahc.model.pacientes.dto.AntecedentePatologicoDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AntecedentePatologicoDTOService {
    AntecedentPatologicoDTOResponse toDTOResponse(AntecedentePatologico antecedentePatologico);
    AntecedentePatologico toEntity(AntecedentePatologicoDTORequest antecedentePatologicoDTORequest);
    AntecedentPatologicoDTOResponse createAntecedentePatologico(AntecedentePatologicoDTORequest antecedentePatologico);
    Page<AntecedentPatologicoDTOResponse> getAntecedentesPatologicosByPacienteId(Integer id, Pageable pageable);
}
