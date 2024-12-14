package mx.sgahc.service.citas.dto;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.dto.CitaDTORequest;
import mx.sgahc.model.citas.dto.CitaDTOResponse;
import mx.sgahc.repository.citas.CitaRepository;
import mx.sgahc.repository.medicos.MedicoRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import mx.sgahc.service.citas.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CitaDTOServiceImpl implements CitaDTOService{
    private final CitaService citaService;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;

    @Autowired
    public CitaDTOServiceImpl(CitaService citaService, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, CitaRepository citaRepository) {
        this.citaService = citaService;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.citaRepository = citaRepository;
    }

    @Override
    public CitaDTOResponse toDTOResponse(Cita cita) {
        if (cita != null){
            CitaDTOResponse citaDTOResponse = new CitaDTOResponse();
            if (cita.getId() != null)
                citaDTOResponse.setId(cita.getId());
            if (cita.getFecha() != null)
                citaDTOResponse.setFecha(cita.getFecha());
            if (cita.getPaciente() != null)
                citaDTOResponse.setPaciente(
                        cita.getPaciente().getDatosPersonales().getNombre()+ " " + cita.getPaciente().getDatosPersonales().getApellido1()
                        + " " + cita.getPaciente().getDatosPersonales().getTelefono() + " "
                                + cita.getPaciente().getDatosPersonales().getUsuario().getCorreoElectronico()
                );
            if (cita.getMedico() != null)
                citaDTOResponse.setMedico(
                        cita.getMedico().getDatosPersonales().getNombre() + " " + cita.getMedico().getDatosPersonales().getApellido1()
                        + " " + cita.getMedico().getDatosPersonales().getTelefono() + " "
                                + cita.getMedico().getDatosPersonales().getUsuario().getCorreoElectronico()
                );
            return citaDTOResponse;
        }

        return null;
    }

    @Override
    public CitaDTORequest toDTORequest(Cita cita) {
        if (cita != null) {
            CitaDTORequest citaDTORequest = new CitaDTORequest();
            if (cita.getId() != null)
                citaDTORequest.setId(cita.getId());
            if (cita.getFecha() != null)
                citaDTORequest.setFecha(cita.getFecha());
            if (cita.getPaciente() != null)
                citaDTORequest.setPacienteId(cita.getPaciente().getId());
            if (cita.getMedico() != null)
                citaDTORequest.setMedicoId(cita.getMedico().getId());
            return citaDTORequest;
        }
        return null;
    }

    @Override
    public Cita requestToEntity(CitaDTORequest citaDTORequest) {
        if (citaDTORequest != null) {
            Cita cita = new Cita();
            if (citaDTORequest.getId() != null)
                cita.setId(citaDTORequest.getId());
            if (citaDTORequest.getFecha() != null)
                cita.setFecha(citaDTORequest.getFecha());
            if (citaDTORequest.getPacienteId() != null)
                cita.setPaciente(
                        pacienteRepository.findById(citaDTORequest.getPacienteId()).orElse(null)
                );
            if (citaDTORequest.getMedicoId() != null)
                cita.setMedico(
                        medicoRepository.findById(citaDTORequest.getMedicoId()).orElse(null)
                );
            return cita;

        }
        return null;
    }

    @Override
    public CitaDTOResponse getCitaResponse(int id) {
        return this.toDTOResponse(
                citaService.getCita(id)
        );
    }

    @Override
    public CitaDTORequest getCitaRequest(int id) {
        return this.toDTORequest(
                citaService.getCita(id)
        );
    }

    @Override
    public List<CitaDTOResponse> getCitasResponseByMedicoAndFecha(Integer id, Date fecha) {
        return citaService.getCitasByMedicoAndFecha(id, fecha).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public List<CitaDTOResponse> getCitasResponseByPacienteAndFecha(Integer id, Date fecha) {
        return citaService.getCitasByPacienteAndFecha(id, fecha).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public List<CitaDTOResponse> getCitasResponseSinDiagnosticoByMedicoId(Integer id) {
        return citaService.getCitasSinDiagnosticoByMedicoId(id).stream().map(this::toDTOResponse).toList();
    }

    @Override
    public Page<CitaDTOResponse> getCitasResponsePendientesByMedicoId(Integer id, Pageable pageable) {
        List<CitaDTOResponse> citas =
                citaService.getCitasPendientesByMedicoId(id, pageable).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(citas, pageable, citas.size());
    }

    @Override
    public Page<CitaDTOResponse> getCitasResponsePendientesByPacienteId(Integer id, Pageable pageable) {
        List<CitaDTOResponse> citas =
                citaService.getCitasPendientesByPacienteId(id, pageable).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(citas, pageable, citas.size());
    }

    @Override
    public Page<CitaDTOResponse> getCitasByMedicoId(Integer id, Pageable pageable) {
        List<CitaDTOResponse> citas =
                citaRepository.findCitaByMedico_Id(id).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(citas, pageable, citas.size());
    }

    @Override
    public Page<CitaDTOResponse> getCitasByPacienteId(Integer id, Pageable pageable) {
        List<CitaDTOResponse> citas =
                citaRepository.findCitaByPaciente_Id(id).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(citas, pageable, citas.size());
    }

    @Override
    public void saveCitaRequest(CitaDTORequest citaDTORequest) {
        Cita cita = this.requestToEntity(citaDTORequest);
        citaService.saveCita(cita);
    }

    @Override
    public void deleteCitaId(Integer id) {
        citaService.deleteCita(id);
    }
}
