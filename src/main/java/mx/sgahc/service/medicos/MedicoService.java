package mx.sgahc.service.medicos;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicoService {
    List<Medico> getMedicos();
    Medico getMedico(int id);
    void saveMedico(Medico medico);
    void updateMedico(Medico medico);
    void deleteMedico(int id);
    Medico getMedicoByDatosPersonales(Integer id);
    Medico getMedicoByUsuarioId(Integer id);
    Page<Cita> getCitas(Integer id, Pageable pageable);
    Page<Paciente> getPacientes(Integer id, Pageable pageable);
    Page<Medico> getMedicosEspecialidadYEstatod(Especialidad especialidad, String estado, Pageable pageable);
}
