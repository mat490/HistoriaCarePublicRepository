package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PacienteDTOService {
    PacienteDTOResponse toDtoResponse(Paciente paciente);
    PacienteDTORequest toDtoRequest(Paciente paciente);
    Paciente responseToEntity(PacienteDTOResponse pacienteDTOResponse);
    Paciente requestToEntity(PacienteDTORequest pacienteDTORequest);
    PacienteDTOResponse getPacienteDTOResponse(Integer id);
    PacienteDTORequest getPacienteDTORequest(Integer id);
    List<PacienteDTOResponse> getPacienteDTOResponses();
    List<PacienteDTOResponse> getPacienteDTOResponsesByMedicoId(Integer id);
    List<PacienteDTORequest> getPacienteDTORequests();
    Page<PacienteDTOResponse> getPacientesByMedicoId(Integer id, Pageable pageable);
    PacienteDTOResponse getPacienteDTOResponseByUsuarioId(Integer id);
    PacienteDTORequest getPacienteDTORequestByUsuarioId(Integer id);
    void savePaciente(PacienteDTORequest pacienteDTORequest);
    boolean validarAccesoPacienteDatos(Integer userId, PacienteDTORequest paciente);
}
