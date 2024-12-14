package mx.sgahc.service.citas;

import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiagnosticoService {
    Diagnostico getDiagnosticoById(Integer id);
    List<Diagnostico> getAllDiagnosticos();
    Diagnostico getDiagnosticoByCitaId(Integer citaId);
    Page<Diagnostico> getDiagnosticosByPacienteId(Integer pacienteId, Pageable pageable);
    Page<Diagnostico> getDiagnosticosByMedicoId(Integer medicoId, Pageable pageable);
    List<Diagnostico> getAllDiagnosticosListByMedicoId(Integer medicoId);
    List<Diagnostico> getDIagnosticosSinTratamientoByMedicoId(Integer medicoId);
    Diagnostico updateDiagnostico(Diagnostico diagnostico);
    Boolean deleteDiagnostico(Integer id);
    void saveDiagnostico(Diagnostico diagnostico);
    boolean validarAccesoDeTratamiento(Integer usuarioId, Paciente paciente);
}
