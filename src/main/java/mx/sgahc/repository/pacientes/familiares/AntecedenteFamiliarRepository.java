package mx.sgahc.repository.pacientes.familiares;

import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AntecedenteFamiliarRepository extends JpaRepository<AntecedenteFamiliar, Integer> {
    Page<AntecedenteFamiliar> findAntecedenteFamiliarByPaciente_Id(Integer pacienteId, Pageable pageable);
    List<AntecedenteFamiliar> findAntecedenteFamiliarByPaciente_Id(Integer pacienteId);

    @Query("SELECT a FROM AntecedenteFamiliar a WHERE a.paciente.id = :pacienteId")
    List<AntecedenteFamiliar> encontrarAntecedentesFamiliaresListByPacienteId(@Param("pacienteId") Integer pacienteId);

}
