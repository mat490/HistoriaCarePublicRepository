package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.repository.medicos.MedicoRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.medicos.dto.MedicoDTOService;
import mx.sgahc.service.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteDTOServiceImpl implements PacienteDTOService {
    private final PacienteService pacienteService;
    private final DatosService datosService;
    private final LugarNacimientoDTOService lugarNacimientoDTOService;
    private final CombeService combeService;
    private final RhService rhService;
    private final GrupoSanguineoService grupoSanguineoService;
    private final OcupacionService ocupacionService;
    private final EstadoCivilService estadoCivilService;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    @Autowired
    public PacienteDTOServiceImpl(PacienteService pacienteService,
                                  DatosService datosService,
                                  LugarNacimientoDTOService lugarNacimientoDTOService,
                                  CombeService combeService,
                                  RhService rhService,
                                  GrupoSanguineoService grupoSanguineo,
                                  OcupacionService ocupacion,
                                  EstadoCivilService estadoCivilService, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.pacienteService = pacienteService;
        this.datosService = datosService;
        this.lugarNacimientoDTOService = lugarNacimientoDTOService;
        this.combeService = combeService;
        this.rhService = rhService;
        this.grupoSanguineoService = grupoSanguineo;
        this.ocupacionService = ocupacion;
        this.estadoCivilService = estadoCivilService;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public PacienteDTOResponse toDtoResponse(Paciente paciente) {
        PacienteDTOResponse dtoResponse = new PacienteDTOResponse();
        if (paciente != null) {
            if (paciente.getId() != null) {
                dtoResponse.setId(paciente.getId());
            }
            if (paciente.getDatosPersonales() != null) {
                dtoResponse.setDatosPersonales(
                        paciente.getDatosPersonales().getNombre() + " " + paciente.getDatosPersonales().getApellido1() + " "
                );
                dtoResponse.setTelefono(paciente.getDatosPersonales().getTelefono());
                dtoResponse.setCorreoElectronico(paciente.getDatosPersonales().getUsuario().getCorreoElectronico());
            }
            if (paciente.getGrupoSanguineo() != null)
                dtoResponse.setGrupoSanguineo(paciente.getGrupoSanguineo().getGrupoSanguineo());
            if (paciente.getRh() != null)
                dtoResponse.setRh(paciente.getRh().getRh());
            if (paciente.getLugarNacimiento() != null)
                dtoResponse.setLugarNacimiento(
                       paciente.getLugarNacimiento().getPais() + " " + paciente.getLugarNacimiento().getEstado()+ " "
                               + paciente.getLugarNacimiento().getMunicipio()
                );
            if (paciente.getOcupacion() != null)
                dtoResponse.setOcupacion(paciente.getOcupacion().getOcupacion());
            if (paciente.getCombe() != null)
                dtoResponse.setCombe(paciente.getCombe().getCombe());
            if (paciente.getEstadoCivil() != null)
                dtoResponse.setEstadoCivil(paciente.getEstadoCivil().getEstadoCivil());
            return dtoResponse;

        }
        return null;
    }

    @Override
    public PacienteDTORequest toDtoRequest(Paciente paciente) {
        PacienteDTORequest dtoRequest = new PacienteDTORequest();
        if (paciente != null) {
            if (paciente.getId() != null) {
                dtoRequest.setId(paciente.getId());
            }
            if (paciente.getDatosPersonales() != null)
                dtoRequest.setDatosPersonalesId(paciente.getDatosPersonales().getId());
            if (paciente.getGrupoSanguineo() != null)
                dtoRequest.setGrupoSanguineoId(paciente.getGrupoSanguineo().getId());
            if (paciente.getRh() != null)
                dtoRequest.setRhId(paciente.getRh().getId());
            if (paciente.getLugarNacimiento() != null)
                dtoRequest.setLugarNacimiento(lugarNacimientoDTOService.toDto(paciente.getLugarNacimiento()));
            if (paciente.getOcupacion() != null)
                dtoRequest.setOcupacionId(paciente.getOcupacion().getId());
            if (paciente.getCombe() != null)
                dtoRequest.setCombeId(paciente.getCombe().getId());
            if (paciente.getEstadoCivil() != null)
                dtoRequest.setEstadoCivilId(paciente.getEstadoCivil().getId());
            return dtoRequest;
        }
        return null;
    }

    @Override
    public Paciente responseToEntity(PacienteDTOResponse pacienteDTOResponse) {
        Paciente entity = new Paciente();
        if (pacienteDTOResponse != null) {
            if (pacienteDTOResponse.getId() != null) {
                entity = pacienteService.getPacienteById(pacienteDTOResponse.getId());
                return entity;
            }
        }

        return null;
    }

    @Override
    public Paciente requestToEntity(PacienteDTORequest pacienteDTORequest) {
        if (pacienteDTORequest != null) {
            Paciente entity = new Paciente();
            if (pacienteDTORequest.getId() != null) {
                entity = pacienteService.getPacienteById(pacienteDTORequest.getId());
                return entity;
            }
            if (pacienteDTORequest.getLugarNacimiento() != null)
                entity.setLugarNacimiento(lugarNacimientoDTOService.toEntity(pacienteDTORequest.getLugarNacimiento()));
            if (pacienteDTORequest.getCombeId() != null)
                entity.setCombe(combeService.getCombeById(pacienteDTORequest.getCombeId()));
            if (pacienteDTORequest.getEstadoCivilId() != null)
                entity.setEstadoCivil(estadoCivilService.getEstadoCivilById(pacienteDTORequest.getEstadoCivilId()));
            if (pacienteDTORequest.getOcupacionId() != null)
                entity.setOcupacion(ocupacionService.getOcupacionById(pacienteDTORequest.getOcupacionId()));
            if (pacienteDTORequest.getGrupoSanguineoId() != null)
                entity.setGrupoSanguineo(grupoSanguineoService.getGrupoSanguineoById(pacienteDTORequest.getGrupoSanguineoId()));
            if (pacienteDTORequest.getRhId() != null)
                entity.setRh(rhService.getRhById(pacienteDTORequest.getRhId()));
            if (pacienteDTORequest.getDatosPersonalesId() != null)
                entity.setDatosPersonales(datosService.getDatosPersonales(pacienteDTORequest.getDatosPersonalesId()));
            return entity;

        }
        return null;
    }

    @Override
    public PacienteDTOResponse getPacienteDTOResponse(Integer id) {
        return toDtoResponse(pacienteService.getPacienteById(id));
    }

    @Override
    public PacienteDTORequest getPacienteDTORequest(Integer id) {
        return toDtoRequest(pacienteService.getPacienteById(id));
    }

    @Override
    public List<PacienteDTOResponse> getPacienteDTOResponses() {
        return pacienteService.getPacientes().stream().map(this::toDtoResponse).toList();
    }

    @Override
    public List<PacienteDTOResponse> getPacienteDTOResponsesByMedicoId(Integer id) {
        return medicoRepository.buscarPacientesPorMedicoId(id).stream().map(this::toDtoResponse).toList();
    }


    @Override
    public List<PacienteDTORequest> getPacienteDTORequests() {
        return pacienteService.getPacientes().stream().map(this::toDtoRequest).toList();
    }


    @Override
    public Page<PacienteDTOResponse> getPacientesByMedicoId(Integer id, Pageable pageable) {
        List<PacienteDTOResponse> pacienteDTOResponses =
                pacienteService.getPacientes().stream().map(this::toDtoResponse).toList();
        return new PageImpl<>(pacienteDTOResponses, pageable, pacienteDTOResponses.size());
    }

    @Override
    public PacienteDTOResponse getPacienteDTOResponseByUsuarioId(Integer id) {
        return toDtoResponse(pacienteService.getPacienteByUsuarioId(id));
    }

    @Override
    public PacienteDTORequest getPacienteDTORequestByUsuarioId(Integer id) {
        return toDtoRequest(pacienteService.getPacienteByUsuarioId(id));
    }

    @Override
    public void savePaciente(PacienteDTORequest pacienteDTORequest) {
        Paciente paciente = this.requestToEntity(pacienteDTORequest);
        pacienteService.savePaciente(paciente);
    }

    @Override
    public boolean validarAccesoPacienteDatos(Integer userId, PacienteDTORequest paciente) {
        Paciente pac = requestToEntity(paciente);
        return pacienteService.validarAccesoPacienteDatos(userId, pac);
    }
}
