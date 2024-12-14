package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.AntecedentePatologico;
import mx.sgahc.model.pacientes.dto.AntecedentPatologicoDTOResponse;
import mx.sgahc.model.pacientes.dto.AntecedentePatologicoDTORequest;
import mx.sgahc.repository.enfermedades.EnferemdadRepository;
import mx.sgahc.repository.pacientes.AntecedentePatologicoRepository;
import mx.sgahc.repository.pacientes.PacienteRepository;
import mx.sgahc.service.pacientes.AntecedentePatologicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedentePatologicoDTOServiceImpl implements AntecedentePatologicoDTOService {
    private final AntecedentePatologicoService antecedentePatologicoService;
    private final EnferemdadRepository enferemdadRepository;
    private final PacienteRepository pacienteRepository;
    private final AntecedentePatologicoRepository antecedentePatologicoRepository;

    @Autowired
    public AntecedentePatologicoDTOServiceImpl(AntecedentePatologicoService antecedentePatologicoService,
                                               EnferemdadRepository enferemdadRepository,
                                               PacienteRepository pacienteRepository,
                                               AntecedentePatologicoRepository antecedentePatologicoRepository) {
        this.antecedentePatologicoService = antecedentePatologicoService;
        this.enferemdadRepository = enferemdadRepository;
        this.pacienteRepository = pacienteRepository;
        this.antecedentePatologicoRepository = antecedentePatologicoRepository;
    }

    @Override
    public AntecedentPatologicoDTOResponse toDTOResponse(AntecedentePatologico antecedentePatologico) {
        if (antecedentePatologico != null) {
            AntecedentPatologicoDTOResponse dtoResponse = new AntecedentPatologicoDTOResponse();
            if (antecedentePatologico.getId() != null) {
                dtoResponse.setId(antecedentePatologico.getId());
            }
            if (antecedentePatologico.getEnfermedad() != null)
                dtoResponse.setEnfermedad(
                        antecedentePatologico.getEnfermedad().getEnfermedad()
                );
            if (antecedentePatologico.getPaciente() != null)
                dtoResponse.setPaciente(
                        antecedentePatologico.getPaciente().getDatosPersonales().getNombre() + " "+
                                antecedentePatologico.getPaciente().getDatosPersonales().getApellido1()
                );
            if (antecedentePatologico.getFecha() != null)
                dtoResponse.setFecha(antecedentePatologico.getFecha());
            if (antecedentePatologico.getDescripcion() != null)
                dtoResponse.setDescripcion(antecedentePatologico.getDescripcion());
            return dtoResponse;
        }
        return null;
    }

    @Override
    public AntecedentePatologico toEntity(AntecedentePatologicoDTORequest antecedentePatologicoDTORequest) {
        if (antecedentePatologicoDTORequest != null) {
            AntecedentePatologico antecedentePatologico = new AntecedentePatologico();
            if (antecedentePatologicoDTORequest.getId() != null)
                antecedentePatologico.setId(antecedentePatologicoDTORequest.getId());
            if (antecedentePatologicoDTORequest.getEnfermedadId() != null)
                antecedentePatologico.setEnfermedad(
                        enferemdadRepository.findById(antecedentePatologicoDTORequest.getEnfermedadId()).orElse(null)
                );
            if (antecedentePatologicoDTORequest.getPacienteId() != null)
                antecedentePatologico.setPaciente(
                        pacienteRepository.findById(antecedentePatologicoDTORequest.getPacienteId()).orElse(null)
                );
            if (antecedentePatologicoDTORequest.getFecha() != null)
                antecedentePatologico.setFecha(antecedentePatologicoDTORequest.getFecha());
            if (antecedentePatologicoDTORequest.getDescripcion() != null)
                antecedentePatologico.setDescripcion(
                        antecedentePatologicoDTORequest.getDescripcion()
                );
            return antecedentePatologico;
        }
        return null;
    }

    @Override
    public AntecedentPatologicoDTOResponse createAntecedentePatologico(AntecedentePatologicoDTORequest antecedentePatologico) {
        AntecedentePatologico antecedentePatologicoEntity = this.toEntity(antecedentePatologico);
        return this.toDTOResponse(antecedentePatologicoService.createAntecedentePatologico(antecedentePatologicoEntity));
    }

    @Override
    public Page<AntecedentPatologicoDTOResponse> getAntecedentesPatologicosByPacienteId(Integer id, Pageable pageable) {
        List<AntecedentPatologicoDTOResponse> antecedentes =
                antecedentePatologicoRepository.findAntecedentePatologicoByPaciente_Id(id).stream().map(this::toDTOResponse).toList();
        return new PageImpl<>(antecedentes, pageable, antecedentes.size());
    }
}
