package mx.sgahc.service.pacientes;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PacienteService {
    Paciente getPacienteById(Integer id);
    List<Paciente> getPacientes();
    void savePaciente(Paciente paciente);
    void updatePaciente(Paciente paciente);
    void deletePaciente(Paciente paciente);
    Page<Medico> getMedicos(Integer id, Pageable pageable);
    Page<Cita> getCitas(Integer id, Pageable pageable);
    Paciente getPacienteByDatosPersonales(Integer id);
    Paciente getPacienteByUsuarioId(Integer id);
    boolean validarAccesoPacienteDatos(Integer userId, Paciente paciente);
}
