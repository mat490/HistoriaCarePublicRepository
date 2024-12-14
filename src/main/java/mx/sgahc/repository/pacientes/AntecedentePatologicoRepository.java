package mx.sgahc.repository.pacientes;

import mx.sgahc.model.pacientes.AntecedentePatologico;
import mx.sgahc.repository.ConteoPorEnfermedad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AntecedentePatologicoRepository extends JpaRepository<AntecedentePatologico, Integer> {
    List<ConteoPorEnfermedad> contarPorEnfermedad();
    Page<AntecedentePatologico> findAntecedentePatologicoByPaciente_Id(Integer id, Pageable pageable);
    List<AntecedentePatologico> findAntecedentePatologicoByPaciente_Id(Integer id);
    AntecedentePatologico findAntecedentePatologicoByEnfermedad_IdAndPaciente_Id(Integer enfermedad_id, Integer paciente_id);
}
