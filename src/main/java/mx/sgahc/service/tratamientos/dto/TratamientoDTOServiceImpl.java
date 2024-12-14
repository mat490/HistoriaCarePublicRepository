package mx.sgahc.service.tratamientos.dto;

import mx.sgahc.model.enfermedades.Tratamiento;
import mx.sgahc.model.enfermedades.dto.TratamientoDTORequest;
import mx.sgahc.model.enfermedades.dto.TratamientoDTOResponse;
import mx.sgahc.repository.citas.DiagnosticoRepository;
import mx.sgahc.repository.enfermedades.MedicamentoRepository;
import mx.sgahc.repository.enfermedades.TratamientoRepository;
import mx.sgahc.service.tratamientos.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamientoDTOServiceImpl implements TratamientoDTOService {
    private final TratamientoService tratamientoService;
    private final TratamientoRepository tratamientoRepository;
    private final DiagnosticoRepository diagnosticoRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final ServerProperties serverProperties;

    @Autowired
    public TratamientoDTOServiceImpl(TratamientoService tratamientoService, TratamientoRepository tratamientoRepository, DiagnosticoRepository diagnosticoRepository, MedicamentoRepository medicamentoRepository, ServerProperties serverProperties) {
        this.tratamientoService = tratamientoService;
        this.tratamientoRepository = tratamientoRepository;
        this.diagnosticoRepository = diagnosticoRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.serverProperties = serverProperties;
    }

    @Override
    public TratamientoDTOResponse toDTOResponse(Tratamiento tratamiento) {
        if (tratamiento != null) {
            TratamientoDTOResponse dtoResponse = new TratamientoDTOResponse();
            if (tratamiento.getId() != null)
                dtoResponse.setId(tratamiento.getId());
            if (tratamiento.getDiagnostico() != null) {
                dtoResponse.setDiagnostico(
                        tratamiento.getDiagnostico().getTitulo() + " " + tratamiento.getDiagnostico().getDescripcion()
                );
                dtoResponse.setDiagnosticoId(tratamiento.getDiagnostico().getId());
                dtoResponse.setPaciente(
                        tratamiento.getDiagnostico().getCita().getPaciente().getDatosPersonales().getNombre()+ " "
                        + " " + tratamiento.getDiagnostico().getCita().getPaciente().getDatosPersonales().getApellido1()
                );
                dtoResponse.setMedico(
                        tratamiento.getDiagnostico().getCita().getMedico().getDatosPersonales().getNombre() + " "
                        + " " + tratamiento.getDiagnostico().getCita().getMedico().getDatosPersonales().getApellido1()
                );
            }
            if (tratamiento.getMedicamento() != null)
                dtoResponse.setMedicamento(
                        tratamiento.getMedicamento().getNombreGenerico()
                );
            if (tratamiento.getDosis() != null)
                dtoResponse.setDosis(tratamiento.getDosis());
            if (tratamiento.getDuracion() != null)
                dtoResponse.setDuracion(tratamiento.getDuracion());
            if (tratamiento.getFechaInicio() != null)
                dtoResponse.setFechaInicio(tratamiento.getFechaInicio());
            if (tratamiento.getFrecuencia() != null)
                dtoResponse.setFrecuencia(tratamiento.getFrecuencia());
            if (tratamiento.getNotasAdicionales() != null)
                dtoResponse.setNotasAdicionales(
                        tratamiento.getNotasAdicionales()
                );
            return dtoResponse;
        }
        return null;
    }

    @Override
    public TratamientoDTORequest toDTORequest(Tratamiento tratamiento) {
        if (tratamiento != null) {
            TratamientoDTORequest dtoRequest = new TratamientoDTORequest();
            if (tratamiento.getId() != null)
                dtoRequest.setId(tratamiento.getId());
            if (tratamiento.getDiagnostico() != null)
                dtoRequest.setDiagnosticoId(tratamiento.getDiagnostico().getId());
            if (tratamiento.getMedicamento() != null)
                dtoRequest.setMedicamentoId(tratamiento.getMedicamento().getId());
            if (tratamiento.getDosis() != null)
                dtoRequest.setDosis(tratamiento.getDosis());
            if (tratamiento.getDuracion() != null)
                dtoRequest.setDuracion(tratamiento.getDuracion());
            if (tratamiento.getFechaInicio() != null)
                dtoRequest.setFechaInicio(tratamiento.getFechaInicio());
            if (tratamiento.getFrecuencia() != null)
                dtoRequest.setFrecuencia(tratamiento.getFrecuencia());
            if (tratamiento.getNotasAdicionales() != null)
                dtoRequest.setNotasAdicionales(tratamiento.getNotasAdicionales());
            return dtoRequest;
        }
        return null;
    }

    @Override
    public Tratamiento requestToEntity(TratamientoDTORequest tratamientoDTORequest) {
        if (tratamientoDTORequest != null) {
            Tratamiento tratamiento = new Tratamiento();
            if (tratamientoDTORequest.getId() != null)
                tratamiento.setId(tratamientoDTORequest.getId());
            if (tratamientoDTORequest.getDiagnosticoId() != null)
                tratamiento.setDiagnostico(
                        diagnosticoRepository.findById(tratamientoDTORequest.getDiagnosticoId()).orElse(null)
                );
            if (tratamientoDTORequest.getMedicamentoId() != null)
                tratamiento.setMedicamento(
                        medicamentoRepository.findById(tratamientoDTORequest.getMedicamentoId()).orElse(null)
                );
            if (tratamientoDTORequest.getFrecuencia() != null)
                tratamiento.setFrecuencia(tratamientoDTORequest.getFrecuencia());
            if (tratamientoDTORequest.getDosis() != null)
                tratamiento.setDosis(tratamientoDTORequest.getDosis());
            if (tratamientoDTORequest.getFechaInicio() != null)
                tratamiento.setFechaInicio(tratamientoDTORequest.getFechaInicio());
            if (tratamientoDTORequest.getNotasAdicionales() != null)
                tratamiento.setNotasAdicionales(tratamientoDTORequest.getNotasAdicionales());
            if (tratamientoDTORequest.getDuracion() != null)
                tratamiento.setDuracion(tratamientoDTORequest.getDuracion());
            return tratamiento;
        }
        return null;
    }

    @Override
    public Tratamiento responseToEntity(TratamientoDTOResponse tratamientoDTO) {
        if (tratamientoDTO != null) {
            Tratamiento tratamiento = new Tratamiento();
            if (tratamientoDTO.getId() != null) {
                tratamiento = tratamientoRepository.findById(tratamientoDTO.getId()).orElse(null);
                return tratamiento;
            }
        }
        return null;
    }

    @Override
    public TratamientoDTOResponse getTratamientoResponseById(Integer idTratamiento) {
        return this.toDTOResponse(tratamientoService.getTratamientoById(idTratamiento));
    }

    @Override
    public TratamientoDTOResponse getTratamientoResponseByDiagnosticoId(Integer diagnosticoId) {
        return this.toDTOResponse(tratamientoService.getTratamientoByDiagnosticoId(diagnosticoId));
    }

    @Override
    public List<TratamientoDTOResponse> getTratamientosByPacienteId(Integer idPaciente) {
        return tratamientoService.getTratamientosByPacienteId(idPaciente).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public List<TratamientoDTOResponse> getTratamientosResponseByPacienteId(Integer idPaciente) {
        return tratamientoService.getTratamientosByPacienteId(idPaciente).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public Page<TratamientoDTOResponse> getTratamientosResponseByPacienteIdPage(Integer idPaciente, Pageable pageable) {
        List<TratamientoDTOResponse> tratamientos =
                tratamientoRepository.findTratamientoByDiagnostico_Cita_Paciente_Id(idPaciente).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(tratamientos, pageable, tratamientos.size());
    }

    @Override
    public TratamientoDTOResponse saveTratamiento(TratamientoDTORequest tratamiento) {
        Tratamiento tratamientoNuevo = this.requestToEntity(tratamiento);
        return this.toDTOResponse(tratamientoService.saveTratamiento(tratamientoNuevo));
    }

    @Override
    public List<TratamientoDTOResponse> getTratamientosResponseByMedicamentoId(Integer idMedicamento) {
        return tratamientoService.getTratamientosByMedicamentoId(idMedicamento).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public boolean validarAccesoDeTratamiento(Integer idUsuario, TratamientoDTORequest tratamiento) {
        Tratamiento trataminetoEntity = this.requestToEntity(tratamiento);
        return tratamientoService.validarAccesoDeTratamiento(idUsuario, trataminetoEntity);
    }

    @Override
    public boolean validarAccesoDeTratamientoResponse(Integer idUsuario, TratamientoDTOResponse tratamiento) {
        Tratamiento trataminetoEntity = this.responseToEntity(tratamiento);
        return tratamientoService.validarAccesoDeTratamiento(idUsuario, trataminetoEntity);
    }
}
