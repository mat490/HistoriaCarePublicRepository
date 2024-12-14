package mx.sgahc.service.tratamientos;

import mx.sgahc.model.enfermedades.Tratamiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TratamientoService {
    Tratamiento getTratamientoById(Integer idTratamiento);
    Tratamiento getTratamientoByDiagnosticoId(Integer diagnosticoId);
    List<Tratamiento> getTratamientosByPacienteId(Integer idPaciente);
    Page<Tratamiento> getTratamientosByPacienteIdPage(Integer idPaciente, Pageable pageable);
    Tratamiento saveTratamiento(Tratamiento tratamiento);
    List<Tratamiento> getTratamientosByMedicamentoId(Integer idMedicamento);
    boolean validarAccesoDeTratamiento(Integer idUsuario, Tratamiento tratamiento);

}
