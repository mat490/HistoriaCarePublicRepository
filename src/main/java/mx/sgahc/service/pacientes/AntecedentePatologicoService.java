package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.AntecedentePatologico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AntecedentePatologicoService {
    AntecedentePatologico createAntecedentePatologico(AntecedentePatologico antecedentePatologico);
    AntecedentePatologico getAntecedentePatologicoById(Integer id);
    List<AntecedentePatologico> getAllAntecedentePatologicos();
    Page<AntecedentePatologico> getAntecedentesPatologicosByPacienteId(Integer id, Pageable pageable);
}
