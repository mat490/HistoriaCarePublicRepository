package mx.sgahc.service.citas.dto;

import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.citas.dto.DiagnosticoDTORequest;
import mx.sgahc.model.citas.dto.DiagnosticoDTOResponse;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiagnosticoDTOService {
    DiagnosticoDTOResponse toDTOResponse(Diagnostico diagnostico);
    DiagnosticoDTORequest toDTORequest(Diagnostico diagnostico);
    Diagnostico requestToEntity(DiagnosticoDTORequest diagnosticoDTORequest);

    DiagnosticoDTOResponse getDiagnosticoResponseById(Integer id);
    DiagnosticoDTORequest getDiagnosticoRequestById(Integer id);
    List<DiagnosticoDTOResponse> getAllDiagnosticosResponse();

    DiagnosticoDTOResponse getDiagnosticoResponseByCitaId(Integer citaId);
    Page<DiagnosticoDTOResponse> getDiagnosticosResponseByPacienteId(Integer pacienteId, Pageable pageable);
    Page<DiagnosticoDTOResponse> getDiagnosticosResponseByMedicoId(Integer medicoId, Pageable pageable);
    List<DiagnosticoDTOResponse> getAllDiagnosticosResponseListByMedicoId(Integer medicoId);
    List<DiagnosticoDTOResponse> getDIagnosticosResponseSinTratamientoByMedicoId(Integer medicoId);
    DiagnosticoDTOResponse updateDiagnostico(DiagnosticoDTORequest diagnostico);
    Boolean deleteDiagnostico(Integer id);
    void saveDiagnostico(DiagnosticoDTORequest diagnostico);
}
