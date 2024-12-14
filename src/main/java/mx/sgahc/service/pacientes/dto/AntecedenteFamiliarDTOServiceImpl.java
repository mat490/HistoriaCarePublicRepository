package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.dto.AntecedenteFamiliarDTORequest;
import mx.sgahc.model.pacientes.dto.AntecedenteFamiliarDTOResponse;
import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;
import mx.sgahc.repository.datos.LugarNacimientoRepository;
import mx.sgahc.repository.datos.SexoRepository;
import mx.sgahc.repository.enfermedades.EnferemdadRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import mx.sgahc.repository.pacientes.familiares.AntecedenteFamiliarRepository;
import mx.sgahc.repository.pacientes.familiares.ParentescoRepository;
import mx.sgahc.repository.pacientes.familiares.RazonFallecimientoRepository;
import mx.sgahc.service.pacientes.AntecedenteFamiliarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedenteFamiliarDTOServiceImpl implements AntecedenteFamiliarDTOService {
    private static final Logger log = LoggerFactory.getLogger(AntecedenteFamiliarDTOServiceImpl.class);
    private final AntecedenteFamiliarService antecedenteFamiliarService;
    private final AntecedenteFamiliarRepository antecedenteFamiliarRepository;
    private final EnferemdadRepository enferemdadRepository;
    private final PacienteRepository pacienteRepository;
    private final ParentescoRepository parentescoRepository;
    private final LugarNacimientoRepository lugarNacimientoRepository;
    private final SexoRepository sexoRepository;
    private final RazonFallecimientoRepository razonFallecimientoRepository;

    @Autowired
    public AntecedenteFamiliarDTOServiceImpl(AntecedenteFamiliarService antecedenteFamiliarService, AntecedenteFamiliarRepository antecedenteFamiliarRepository, EnferemdadRepository enferemdadRepository, PacienteRepository pacienteRepository, ParentescoRepository parentescoRepository, LugarNacimientoRepository lugarNacimientoRepository, SexoRepository sexoRepository, RazonFallecimientoRepository razonFallecimientoRepository) {
        this.antecedenteFamiliarService = antecedenteFamiliarService;
        this.antecedenteFamiliarRepository = antecedenteFamiliarRepository;
        this.enferemdadRepository = enferemdadRepository;
        this.pacienteRepository = pacienteRepository;
        this.parentescoRepository = parentescoRepository;
        this.lugarNacimientoRepository = lugarNacimientoRepository;
        this.sexoRepository = sexoRepository;
        this.razonFallecimientoRepository = razonFallecimientoRepository;
    }

    @Override
    public AntecedenteFamiliarDTOResponse toDTORespomse(AntecedenteFamiliar antecedenteFamiliar) {
        if (antecedenteFamiliar != null) {
            AntecedenteFamiliarDTOResponse antecedenteFamiliarDTOResponse = new AntecedenteFamiliarDTOResponse();
            if (antecedenteFamiliar.getId() != null)
                antecedenteFamiliarDTOResponse.setId(antecedenteFamiliar.getId());
            if (antecedenteFamiliar.getEdad() != null)
                antecedenteFamiliarDTOResponse.setEdad(antecedenteFamiliar.getEdad());
            if (antecedenteFamiliar.getParentesco() != null)
                antecedenteFamiliarDTOResponse.setParentesco(
                        antecedenteFamiliar.getParentesco().getParentesco()
                );
            if (antecedenteFamiliar.getLugarNacimiento() != null)
                antecedenteFamiliarDTOResponse.setLugarNacimiento(
                        antecedenteFamiliar.getLugarNacimiento().getPais()+ ", "
                                + antecedenteFamiliar.getLugarNacimiento().getEstado() + ", "
                                + antecedenteFamiliar.getLugarNacimiento().getMunicipio()
                );
            if (antecedenteFamiliar.getEnfermedad() != null)
                antecedenteFamiliarDTOResponse.setEnfermedad(antecedenteFamiliar.getEnfermedad().getEnfermedad());
            if (antecedenteFamiliar.getRazonFallecimiento() != null)
                antecedenteFamiliarDTOResponse.setRazonFallecimiento(antecedenteFamiliar.getRazonFallecimiento().getRazon());
            if (antecedenteFamiliar.getSexo() != null)
                antecedenteFamiliarDTOResponse.setSexo(
                        antecedenteFamiliar.getSexo().getSexo()
                );
            if (antecedenteFamiliar.getPaciente() != null)
                antecedenteFamiliarDTOResponse.setPaciente(
                        antecedenteFamiliar.getPaciente().getDatosPersonales().getNombre()+ " " +
                                antecedenteFamiliar.getPaciente().getDatosPersonales().getApellido1()
                );
            return antecedenteFamiliarDTOResponse;

        }
        return null;
    }

    @Override
    public AntecedenteFamiliar toEntity(AntecedenteFamiliarDTORequest antecedenteFamiliarDTO) {
        if (antecedenteFamiliarDTO != null){
            AntecedenteFamiliar antecedenteFamiliar = new AntecedenteFamiliar();
            if (antecedenteFamiliarDTO.getId() != null)
                antecedenteFamiliar.setId(antecedenteFamiliarDTO.getId());
            if (antecedenteFamiliarDTO.getEdad() != null)
                antecedenteFamiliar.setEdad(
                        antecedenteFamiliarDTO.getEdad()
                );
            if (antecedenteFamiliarDTO.getEnfermedadId() != null)
                antecedenteFamiliar.setEnfermedad(
                        enferemdadRepository.findById(antecedenteFamiliarDTO.getEnfermedadId()).orElse(null)
                );
            if (antecedenteFamiliarDTO.getPacienteId() != null)
                antecedenteFamiliar.setPaciente(
                        pacienteRepository.findById(antecedenteFamiliarDTO.getPacienteId()).orElse(null)
                );
            if (antecedenteFamiliarDTO.getParentescoId() != null)
                antecedenteFamiliar.setParentesco(
                        parentescoRepository.findById(antecedenteFamiliarDTO.getParentescoId()).orElse(null)
                );
            if (antecedenteFamiliarDTO.getLugarNacimientoId() != null)
                antecedenteFamiliar.setLugarNacimiento(
                        lugarNacimientoRepository.findById(antecedenteFamiliarDTO.getLugarNacimientoId()).orElse(null)
                );
            if (antecedenteFamiliarDTO.getSexoId() != null)
                antecedenteFamiliar.setSexo(
                        sexoRepository.findById(antecedenteFamiliarDTO.getSexoId()).orElse(null)
                );
            if (antecedenteFamiliarDTO.getRazonFallecimientoId() != null)
                antecedenteFamiliar.setRazonFallecimiento(
                        razonFallecimientoRepository.findById(antecedenteFamiliarDTO.getRazonFallecimientoId()).orElse(null)
                );
            return antecedenteFamiliar;
        }
        return null;
    }

    @Override
    public Page<AntecedenteFamiliarDTOResponse> findAntecedentesByPacienteId(Integer id, Pageable pageable) {
        List<AntecedenteFamiliarDTOResponse> antecedentes =
                antecedenteFamiliarRepository
                        .encontrarAntecedentesFamiliaresListByPacienteId(id).stream().map(this::toDTORespomse).toList();
        log.info(id.toString() + " Antecedentes encontrados: " + antecedentes.size());
        log.info("Antecedentes: {}",antecedentes);
        return new PageImpl<>(antecedentes, pageable, antecedentes.size());
    }

    @Override
    public void saveAntecedenteFamiliar(AntecedenteFamiliarDTORequest antecedenteFamiliar) {
        AntecedenteFamiliar antecedenteFamiliarNuevo = this.toEntity(antecedenteFamiliar);
        antecedenteFamiliarService.saveAntecedenteFamiliar(antecedenteFamiliarNuevo);
    }
}
