package mx.sgahc.service.medicos;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.repository.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService{
    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public List<Medico> getMedicos() {
        return List.of();
    }

    @Override
    public Medico getMedico(int id) {

        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMedico(Medico medico) {
        medicoRepository.save(medico);
    }

    @Override
    public void updateMedico(Medico medico) {

    }

    @Override
    public void deleteMedico(int id) {

    }

    @Override
    public Medico getMedicoByDatosPersonales(Integer id) {
        return medicoRepository.findByDatosPersonalesId(id).orElse(null);
    }

    @Override
    public Medico getMedicoByUsuarioId(Integer id) {
        return medicoRepository.findByUsuarioId(id).orElse(null);
    }

    @Override
    public Page<Cita> getCitas(Integer id, Pageable pageable) {
        return medicoRepository.buscarCitasPorMedicoId(id, pageable);
    }

    @Override
    public Page<Paciente> getPacientes(Integer id, Pageable pageable) {

        return medicoRepository.buscarPacientesPorMedicoId(id, pageable);
    }

    @Override
    public Page<Medico> getMedicosEspecialidadYEstatod(Especialidad especialidad, String estado, Pageable pageable) {
        return medicoRepository.buscarMedicosPorEspecialidadYEstado(especialidad, estado, pageable);
    }
}
