package mx.sgahc.service.citas;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.enfermedades.Tratamiento;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.repository.citas.DiagnosticoRepository;
import mx.sgahc.repository.enfermedades.TratamientoRepository;
import mx.sgahc.repository.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {
    private final DiagnosticoRepository diagnosticoRepository;
    private final TratamientoRepository tratamientoRepository;
    private final MedicoRepository medicoRepository;

    @Autowired
    public DiagnosticoServiceImpl(DiagnosticoRepository diagnosticoRepository, TratamientoRepository tratamientoRepository, MedicoRepository medicoRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
        this.tratamientoRepository = tratamientoRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Diagnostico getDiagnosticoById(Integer id) {
        return diagnosticoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Diagnostico> getAllDiagnosticos() {
        return List.of();
    }

    @Override
    public Diagnostico getDiagnosticoByCitaId(Integer citaId) {
        return null;
    }

    @Override
    public Page<Diagnostico> getDiagnosticosByPacienteId(Integer pacienteId, Pageable pageable) {
        return diagnosticoRepository.findDiagnosticosByCita_Paciente_Id(pacienteId, pageable);
    }

    @Override
    public Page<Diagnostico> getDiagnosticosByMedicoId(Integer medicoId, Pageable pageable) {
        return diagnosticoRepository.findDiagnosticosByCita_Medico_Id(medicoId, pageable);
    }

    @Override
    public List<Diagnostico> getAllDiagnosticosListByMedicoId(Integer medicoId) {
        return diagnosticoRepository.searchDiagnosticoByCita_Medico_Id(medicoId);
    }

    @Override
    public List<Diagnostico> getDIagnosticosSinTratamientoByMedicoId(Integer medicoId) {
        return diagnosticoRepository.findDiagnosticosSinTratamientoByMedicoId(medicoId);
    }

    @Override
    public Diagnostico updateDiagnostico(Diagnostico diagnostico) {
        return null;
    }

    @Override
    public Boolean deleteDiagnostico(Integer id) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id).orElse(null);
        if (diagnostico != null) {
            Tratamiento tratamiento = tratamientoRepository.findTratamientoByDiagnostico_Id(id);
            if (tratamiento != null){
                return false;
            }
            diagnosticoRepository.delete(diagnostico);
            return true;
        }
        return false;
    }

    @Override
    public void saveDiagnostico(Diagnostico diagnostico) {
        diagnosticoRepository.save(diagnostico);
    }

    @Override
    public boolean validarAccesoDeTratamiento(Integer usuarioId, Paciente paciente) {
        if (Objects.equals(usuarioId, paciente.getDatosPersonales().getUsuario().getId())) {
            return true;
        }

        return medicoRepository.findByUsuarioId(usuarioId)
                .map(medico -> medico.getPacientes().stream()
                        .anyMatch(pac -> pac.getId().equals(paciente.getDatosPersonales().getUsuario().getId())))
                .orElse(false);
    }
}
