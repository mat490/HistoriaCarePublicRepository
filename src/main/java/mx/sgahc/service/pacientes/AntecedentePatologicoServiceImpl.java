package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.AntecedentePatologico;
import mx.sgahc.repository.pacientes.AntecedentePatologicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedentePatologicoServiceImpl implements AntecedentePatologicoService {
    private final AntecedentePatologicoRepository antecedentePatologicoRepository;

    @Autowired
    public AntecedentePatologicoServiceImpl(AntecedentePatologicoRepository antecedentePatologicoRepository) {
        this.antecedentePatologicoRepository = antecedentePatologicoRepository;
    }

    @Override
    public AntecedentePatologico createAntecedentePatologico(AntecedentePatologico antecedentePatologico) {
        if (antecedentePatologicoRepository.findAntecedentePatologicoByEnfermedad_IdAndPaciente_Id(
                antecedentePatologico.getEnfermedad().getId(), antecedentePatologico.getPaciente().getId()
        ) != null)
        return antecedentePatologicoRepository.save(antecedentePatologico);
        else
            return null;
    }

    @Override
    public AntecedentePatologico getAntecedentePatologicoById(Integer id) {
        return antecedentePatologicoRepository.findById(id).orElse(null);
    }

    @Override
    public List<AntecedentePatologico> getAllAntecedentePatologicos() {
        return antecedentePatologicoRepository.findAll();
    }

    @Override
    public Page<AntecedentePatologico> getAntecedentesPatologicosByPacienteId(Integer id, Pageable pageable) {
        return antecedentePatologicoRepository.findAntecedentePatologicoByPaciente_Id(id, pageable);
    }
}
