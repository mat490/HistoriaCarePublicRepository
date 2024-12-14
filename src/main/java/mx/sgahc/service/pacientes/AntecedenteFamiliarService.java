package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AntecedenteFamiliarService {
    AntecedenteFamiliar findAntecedenteFamiliarById(Integer id);
    List<AntecedenteFamiliar> findAllAntecedenteFamiliar();
    Page<AntecedenteFamiliar> findAntecedentesByPacienteId(Integer id, Pageable pageable);
    AntecedenteFamiliar updateAntecedenteFamiliar(Integer antecedenteid, AntecedenteFamiliar antecedenteFamiliar);
    void deleteAntecedenteFamiliar(Integer antecedenteid);
    void saveAntecedenteFamiliar(AntecedenteFamiliar antecedenteFamiliar);
}
