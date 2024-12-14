package mx.sgahc.service.tratamientos;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.enfermedades.Tratamiento;
import mx.sgahc.model.pacientes.AntecedentePatologico;
import mx.sgahc.repository.enfermedades.TratamientoRepository;
import mx.sgahc.repository.medicos.MedicoRepository;
import mx.sgahc.repository.pacientes.AntecedentePatologicoRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TratamientoServiceImpl implements TratamientoService {
    private final TratamientoRepository tratamientoRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final AntecedentePatologicoRepository antecedentePatologicoRepository;

    @Autowired
    public TratamientoServiceImpl(TratamientoRepository tratamientoRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, AntecedentePatologicoRepository antecedentePatologicoRepository) {
        this.tratamientoRepository = tratamientoRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.antecedentePatologicoRepository = antecedentePatologicoRepository;
    }

    @Override
    public Tratamiento getTratamientoById(Integer idTratamiento) {
        return tratamientoRepository.findById(idTratamiento).orElse(null);
    }

    @Override
    public Tratamiento saveTratamiento(Tratamiento tratamiento) {
        tratamientoRepository.save(tratamiento);
        AntecedentePatologico antecedente = AntecedentePatologico.builder()
                                        .enfermedad(tratamiento.getDiagnostico().getEnfermedad())
                                        .paciente(tratamiento.getDiagnostico().getCita().getPaciente())
                                        .fecha(tratamiento.getDiagnostico().getFecha())
                                        .descripcion(tratamiento.getDiagnostico().getDescripcion()).build();
        antecedentePatologicoRepository.save(antecedente);
        return tratamientoRepository.findTratamientoByDiagnostico_Id(tratamiento.getDiagnostico().getId());

    }

    @Override
    public Tratamiento getTratamientoByDiagnosticoId(Integer diagnosticoId) {
        return null;
    }

    @Override
    public List<Tratamiento> getTratamientosByPacienteId(Integer idPaciente) {
        return tratamientoRepository.findTratamientoByDiagnostico_Cita_Paciente_Id(idPaciente);
    }

    @Override
    public Page<Tratamiento> getTratamientosByPacienteIdPage(Integer idPaciente, Pageable pageable) {
        return tratamientoRepository.findTratamientoByDiagnostico_Cita_Paciente_Id(idPaciente, pageable);
    }

    @Override
    public List<Tratamiento> getTratamientosByMedicamentoId(Integer idMedicamento) {
        return tratamientoRepository.findTratamientoByMedicamento_Id(idMedicamento);
    }

    @Override
    public boolean validarAccesoDeTratamiento(Integer idUsuario, Tratamiento tratamiento) {
        Integer idPacienteUsuario = tratamiento.getDiagnostico().getCita().getPaciente().getDatosPersonales().getUsuario().getId();
        if (idPacienteUsuario.equals(idUsuario)) {
            return true;
        }

        Integer idMedicoUsuario = tratamiento.getDiagnostico().getCita().getMedico().getDatosPersonales().getUsuario().getId();
        if (idMedicoUsuario.equals(idUsuario)) {
            return true;
        }

        return medicoRepository.findByUsuarioId(idUsuario)
                .map(medico -> medico.getPacientes().stream()
                        .anyMatch(paciente -> paciente.getId().equals(tratamiento.getDiagnostico().getCita().getPaciente().getId())))
                .orElse(false);
    }

}
