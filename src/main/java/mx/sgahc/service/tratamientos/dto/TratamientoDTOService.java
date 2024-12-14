package mx.sgahc.service.tratamientos.dto;

import mx.sgahc.model.enfermedades.Tratamiento;
import mx.sgahc.model.enfermedades.dto.TratamientoDTORequest;
import mx.sgahc.model.enfermedades.dto.TratamientoDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TratamientoDTOService {
    TratamientoDTOResponse toDTOResponse(Tratamiento tratamiento);
    TratamientoDTORequest toDTORequest(Tratamiento tratamiento);
    Tratamiento requestToEntity(TratamientoDTORequest tratamientoDTORequest);
    Tratamiento responseToEntity(TratamientoDTOResponse tratamientoDTORequest);
    TratamientoDTOResponse getTratamientoResponseById(Integer idTratamiento);
    TratamientoDTOResponse getTratamientoResponseByDiagnosticoId(Integer diagnosticoId);
    List<TratamientoDTOResponse> getTratamientosByPacienteId(Integer idPaciente);
    List<TratamientoDTOResponse> getTratamientosResponseByPacienteId(Integer idPaciente);
    Page<TratamientoDTOResponse> getTratamientosResponseByPacienteIdPage(Integer idPaciente, Pageable pageable);
    TratamientoDTOResponse saveTratamiento(TratamientoDTORequest tratamiento);
    List<TratamientoDTOResponse> getTratamientosResponseByMedicamentoId(Integer idMedicamento);
    boolean validarAccesoDeTratamiento(Integer idUsuario, TratamientoDTORequest tratamiento);
    boolean validarAccesoDeTratamientoResponse(Integer idUsuario, TratamientoDTOResponse tratamiento);
}
