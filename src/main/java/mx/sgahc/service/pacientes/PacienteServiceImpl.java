package mx.sgahc.service.pacientes;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.repository.medicos.MedicoRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }



    @Override
    public Paciente getPacienteById(Integer id) {
        return pacienteRepository.findById(id).orElse(null);
    }



    @Override
    public List<Paciente> getPacientes() {

        return pacienteRepository.findAll();
    }

    @Override
    public void savePaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void updatePaciente(Paciente paciente) {
        Paciente pacienteOptional = pacienteRepository.findByUsuarioId(paciente.getDatosPersonales().getUsuario().getId());
        if (pacienteOptional != null) {
            pacienteRepository.save(paciente);
        }
    }

    @Override
    public void deletePaciente(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }

    @Override
    public Page<Medico> getMedicos(Integer id, Pageable pageable) {
        return pacienteRepository.findMedicosByPacientesId(id, pageable);
    }

    @Override
    public Page<Cita> getCitas(Integer id, Pageable pageable) {
        return pacienteRepository.buscarCitasPorPacienteId(id, pageable);
    }

    @Override
    public Paciente getPacienteByDatosPersonales(Integer id) {
        return pacienteRepository.findByDatosPersonalesId(id);
    }

    @Override
    public Paciente getPacienteByUsuarioId(Integer id) {
        return pacienteRepository.findByUsuarioId(id);
    }

    @Override
    public boolean validarAccesoPacienteDatos(Integer usuarioId, Paciente paciente) {
        if (paciente != null) {
            if (Objects.equals(usuarioId, paciente.getDatosPersonales().getUsuario().getId())) {
                return true;
            }

            return medicoRepository.findByUsuarioId(usuarioId)
                    .map(medico -> medico.getPacientes().stream()
                            .anyMatch(pac -> pac.getId().equals(paciente.getId())))
                    .orElse(false);
        }
        return false;
    }
}
