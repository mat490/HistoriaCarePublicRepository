package mx.sgahc.service.medicos.dto;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.repository.medicos.MedicoRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import mx.sgahc.service.datos.dto.DatosDTOService;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.pacientes.dto.PacienteDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MedicoDTOServiceImpl implements MedicoDTOService {
    private final MedicoService medicoService;
    private final EspecialidadDTOService especialidadDTOService;
    private final DatosDTOService datosDTOService;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public MedicoDTOServiceImpl(MedicoService medicoService,
                                EspecialidadDTOService especialidadDTOService,
                                DatosDTOService datosDTOService,
                                MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.medicoService = medicoService;
        this.especialidadDTOService = especialidadDTOService;
        this.datosDTOService = datosDTOService;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public MedicoDTO toDTO(Medico medico) {
        if (medico != null) {
            MedicoDTO medicoDTO = new MedicoDTO();
            if (medico.getId() != null)
                medicoDTO.setId(medico.getId());
            if (medico.getCedula() != null)
                medicoDTO.setCedula(medico.getCedula());
            if (medico.getEspecialidad() != null)
                medicoDTO.setEspecialidad(
                        especialidadDTOService.toDTO(medico.getEspecialidad())
                );
            if (medico.getDatosPersonales() != null)
                medicoDTO.setDatosPersonales(
                        datosDTOService.toDTO(medico.getDatosPersonales())
                );
            return medicoDTO;
        }
        return null;
    }

    @Override
    public Medico toEntity(MedicoDTO medicoDTO) {
        if (medicoDTO != null) {
            Medico medico = new Medico();
            if (medicoDTO.getId() != null)
                medico.setId(medicoDTO.getId());
            if (medicoDTO.getCedula() != null)
                medico.setCedula(medicoDTO.getCedula());
            if (medicoDTO.getEspecialidad() != null)
                medico.setEspecialidad(
                  especialidadDTOService.toEntity(medicoDTO.getEspecialidad())
                );
            if (medicoDTO.getDatosPersonales() != null)
                medico.setDatosPersonales(
                        datosDTOService.toEntity(medicoDTO.getDatosPersonales())
                );
            if (medicoDTO.getDatosPersonales() != null)
                medico.setDatosPersonales(
                        datosDTOService.toEntity(medicoDTO.getDatosPersonales())
                );
            return medico;
        }
        return null;
    }

    @Override
    public void saveMedico(MedicoDTO medico) {
        Medico medicoNuevo = this.toEntity(medico);
        log.info("Guardando medico nuevo "+medicoNuevo);
        medicoService.saveMedico(medicoNuevo);
        log.info("Medico nuevo guardado "+medicoNuevo);
    }

    @Override
    public MedicoDTO getMedicoByUsuarioId(Integer id) {
        return this.toDTO(medicoService.getMedicoByUsuarioId(id));
    }

    @Override
    public Page<Cita> getCitas(Integer id, Pageable pageable) {
        return null;
    }


    @Override
    public Page<MedicoDTO> getMedicosEspecialidadYEstatod(Integer especialidad, String estado, Pageable pageable) {
        List<MedicoDTO> medicos =
                medicoRepository.buscarMedicosPorEspecialidadYEstadoList(especialidad, estado, pageable)
                        .stream().map(this::toDTO).toList();
        return new PageImpl<>(medicos, pageable, medicos.size());
    }

    @Override
    public Page<MedicoDTO> getMedicosByPacienteId(Integer id, Pageable pageable) {
        List<MedicoDTO> medicoDTOList =
                pacienteRepository.findMedicosByPacientesId(id).stream().map(this::toDTO).toList();
        return new PageImpl<>(medicoDTOList, pageable, medicoDTOList.size());
    }

    @Override
    public List<MedicoDTO> getMedicosByPacienteIdList(Integer id) {
        return pacienteRepository.findMedicosByPacientesId(id).stream().map(this::toDTO).toList();
    }
}
