package mx.sgahc.service.citas.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.dto.CitaDTORequest;
import mx.sgahc.model.citas.dto.CitaDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CitaDTOService {
    CitaDTOResponse toDTOResponse(Cita cita);
    CitaDTORequest toDTORequest(Cita cita);
    Cita requestToEntity(CitaDTORequest citaDTORequest);
    CitaDTOResponse getCitaResponse(int id);
    CitaDTORequest getCitaRequest(int id);
    List<CitaDTOResponse> getCitasResponseByMedicoAndFecha(Integer id, Date fecha);
    List<CitaDTOResponse> getCitasResponseByPacienteAndFecha(Integer id, Date fecha);
    List<CitaDTOResponse> getCitasResponseSinDiagnosticoByMedicoId(Integer id);
    Page<CitaDTOResponse> getCitasResponsePendientesByMedicoId(Integer id, Pageable pageable);
    Page<CitaDTOResponse> getCitasResponsePendientesByPacienteId(Integer id, Pageable pageable);
    Page<CitaDTOResponse> getCitasByMedicoId(Integer id, Pageable pageable);
    Page<CitaDTOResponse> getCitasByPacienteId(Integer id, Pageable pageable);
    void saveCitaRequest(CitaDTORequest citaDTORequest);
    void deleteCitaId(Integer id);
}
