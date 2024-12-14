package mx.sgahc.service.citas.dto;

import jdk.jshell.Diag;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.citas.dto.DiagnosticoDTORequest;
import mx.sgahc.model.citas.dto.DiagnosticoDTOResponse;
import mx.sgahc.repository.citas.CitaRepository;
import mx.sgahc.repository.citas.DiagnosticoRepository;
import mx.sgahc.repository.enfermedades.EnferemdadRepository;
import mx.sgahc.service.citas.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticoDTOServiceImpl implements DiagnosticoDTOService{
    private final DiagnosticoService diagnosticoService;
    private final EnferemdadRepository enferemdadRepository;
    private final CitaRepository citaRepository;
    private final DiagnosticoRepository diagnosticoRepository;

    @Autowired
    public DiagnosticoDTOServiceImpl(DiagnosticoService diagnosticoService, EnferemdadRepository enferemdadRepository, CitaRepository citaRepository, DiagnosticoRepository diagnosticoRepository) {
        this.diagnosticoService = diagnosticoService;
        this.enferemdadRepository = enferemdadRepository;
        this.citaRepository = citaRepository;
        this.diagnosticoRepository = diagnosticoRepository;
    }

    @Override
    public DiagnosticoDTOResponse toDTOResponse(Diagnostico diagnostico) {
        if (diagnostico != null) {
            DiagnosticoDTOResponse diagnosticoDTOResponse = new DiagnosticoDTOResponse();
            if (diagnostico.getId() != null)
                diagnosticoDTOResponse.setId(diagnostico.getId());
            if (diagnostico.getTitulo() != null)
                diagnosticoDTOResponse.setTitulo(diagnostico.getTitulo());
            if (diagnostico.getDescripcion() != null)
                diagnosticoDTOResponse.setDescripcion(diagnostico.getDescripcion());
            if (diagnostico.getFecha() != null)
                diagnosticoDTOResponse.setFecha(diagnostico.getFecha());
            if (diagnostico.getEnfermedad() != null)
                diagnosticoDTOResponse.setEnfermedad(
                        diagnostico.getEnfermedad().getEnfermedad()
                );
            if (diagnostico.getCita() != null) {
                diagnosticoDTOResponse.setCita(
                        " Paciente: " + diagnostico.getCita().getPaciente().getDatosPersonales().getNombre() + " " +
                                diagnostico.getCita().getPaciente().getDatosPersonales().getApellido1() + " | " +
                                " Medico: " + diagnostico.getCita().getMedico().getDatosPersonales().getNombre() + " " +
                                diagnostico.getCita().getMedico().getDatosPersonales().getApellido1() + " | " +
                                diagnostico.getCita().getFecha()
                );
                diagnosticoDTOResponse.setPaciente(
                        diagnostico.getCita().getPaciente().getDatosPersonales().getNombre() + " " +
                                diagnostico.getCita().getPaciente().getDatosPersonales().getApellido1()
                );
                diagnosticoDTOResponse.setMedico(
                        diagnostico.getCita().getMedico().getDatosPersonales().getNombre() + " " +
                                diagnostico.getCita().getMedico().getDatosPersonales().getApellido1()
                );
            }

            return diagnosticoDTOResponse;

        }
        return null;
    }

    @Override
    public DiagnosticoDTORequest toDTORequest(Diagnostico diagnostico) {
        if (diagnostico != null) {
            DiagnosticoDTORequest diagnosticoDTORequest = new DiagnosticoDTORequest();
            if (diagnostico.getId() != null)
                diagnosticoDTORequest.setId(diagnostico.getId());
            if (diagnostico.getTitulo() != null)
                diagnosticoDTORequest.setTitulo(diagnostico.getTitulo());
            if (diagnostico.getDescripcion() != null)
                diagnosticoDTORequest.setDescripcion(diagnostico.getDescripcion());
            if (diagnostico.getFecha() != null)
                diagnosticoDTORequest.setFecha(diagnostico.getFecha());
            if (diagnostico.getEnfermedad() != null)
                diagnosticoDTORequest.setEnfermedadId(
                        diagnostico.getEnfermedad().getId()
                );
            if (diagnostico.getCita() != null)
                diagnosticoDTORequest.setCitaId(
                        diagnostico.getCita().getId()
                );
            return diagnosticoDTORequest;
        }
        return null;
    }

    @Override
    public Diagnostico requestToEntity(DiagnosticoDTORequest diagnosticoDTORequest) {
        if (diagnosticoDTORequest != null) {
            Diagnostico diagnostico = new Diagnostico();
            if (diagnosticoDTORequest.getId() != null)
                diagnostico.setId(diagnosticoDTORequest.getId());

            if (diagnosticoDTORequest.getTitulo() != null)
                diagnostico.setTitulo(diagnosticoDTORequest.getTitulo());

            if (diagnosticoDTORequest.getDescripcion() != null)
                diagnostico.setDescripcion(diagnosticoDTORequest.getDescripcion());

            if (diagnosticoDTORequest.getFecha() != null)
                diagnostico.setFecha(diagnosticoDTORequest.getFecha());

            if (diagnosticoDTORequest.getEnfermedadId() != null)
                diagnostico.setEnfermedad(
                        enferemdadRepository.findById(diagnosticoDTORequest.getEnfermedadId()).orElse(null)
                );
            if (diagnosticoDTORequest.getCitaId() != null)
                diagnostico.setCita(
                        citaRepository.findById(diagnosticoDTORequest.getCitaId()).orElse(null)
                );
            return diagnostico;
        }
        return null;
    }

    @Override
    public DiagnosticoDTOResponse getDiagnosticoResponseById(Integer id) {
        return this.toDTOResponse(diagnosticoService.getDiagnosticoById(id));
    }

    @Override
    public DiagnosticoDTORequest getDiagnosticoRequestById(Integer id) {
        return this.toDTORequest(diagnosticoService.getDiagnosticoById(id));
    }

    @Override
    public List<DiagnosticoDTOResponse> getAllDiagnosticosResponse() {
        return diagnosticoService.getAllDiagnosticos().stream().map(this::toDTOResponse).toList();
    }

    @Override
    public DiagnosticoDTOResponse getDiagnosticoResponseByCitaId(Integer citaId) {
        return this.toDTOResponse(diagnosticoService.getDiagnosticoByCitaId(citaId));
    }

    @Override
    public Page<DiagnosticoDTOResponse> getDiagnosticosResponseByPacienteId(Integer pacienteId, Pageable pageable) {
        List<DiagnosticoDTOResponse> diagnosticoDTOResponses =
                diagnosticoRepository.findDiagnosticosByCita_Paciente_Id(pacienteId).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(diagnosticoDTOResponses, pageable, diagnosticoDTOResponses.size());
    }

    @Override
    public Page<DiagnosticoDTOResponse> getDiagnosticosResponseByMedicoId(Integer medicoId, Pageable pageable) {
        List<DiagnosticoDTOResponse> diagnosticoDTOResponses =
                diagnosticoRepository.findDiagnosticosByCita_Medico_Id(medicoId).stream().map(this::toDTOResponse).toList();

        return new PageImpl<>(diagnosticoDTOResponses, pageable, diagnosticoDTOResponses.size());
    }

    @Override
    public List<DiagnosticoDTOResponse> getAllDiagnosticosResponseListByMedicoId(Integer medicoId) {
        return diagnosticoService.getAllDiagnosticos().stream().map(this::toDTOResponse).toList();
    }

    @Override
    public List<DiagnosticoDTOResponse> getDIagnosticosResponseSinTratamientoByMedicoId(Integer medicoId) {
        return diagnosticoService.getDIagnosticosSinTratamientoByMedicoId(medicoId).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public DiagnosticoDTOResponse updateDiagnostico(DiagnosticoDTORequest diagnostico) {
        Diagnostico diagnosticoNuevo = this.requestToEntity(diagnostico);
        return this.toDTOResponse(diagnosticoService.updateDiagnostico(diagnosticoNuevo));
    }

    @Override
    public Boolean deleteDiagnostico(Integer id) {
        return diagnosticoService.deleteDiagnostico(id);
    }

    @Override
    public void saveDiagnostico(DiagnosticoDTORequest diagnostico) {
        Diagnostico diagnosticoNuevo = this.requestToEntity(diagnostico);
        diagnosticoService.saveDiagnostico(diagnosticoNuevo);
    }
}
