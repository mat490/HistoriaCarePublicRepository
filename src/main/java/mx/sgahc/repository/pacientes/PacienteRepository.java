package mx.sgahc.repository.pacientes;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Page<Cita> buscarCitasPorPacienteId(Integer id, Pageable pageable);
    Page<Medico> findMedicosByPacientesId(Integer pacienteId, Pageable pageable);
    List<Medico> findMedicosByPacientesId(Integer pacienteId);
    @Query("SELECT p FROM Paciente p WHERE p.datosPersonales.id = :datosPersonalesId")
    Paciente findByDatosPersonalesId(@Param("datosPersonalesId") Integer datosPersonalesId);
    @Query("SELECT p FROM Paciente  p WHERE p.datosPersonales.usuario.id = :usuarioId")
    Paciente findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    Optional<Paciente> searchPacienteById(Integer id);
}
