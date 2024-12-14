package mx.sgahc.repository.citas;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.repository.ConteoPorEnfermedad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {
    List<Diagnostico> searchByEnfermedadNot(Enfermedad enfermedad);
    List<ConteoPorEnfermedad> contarPorEnfermedad();
    Page<Diagnostico> findDiagnosticosByCita_Medico_Id(Integer idMedico, Pageable pageable);
    List<Diagnostico> findDiagnosticosByCita_Medico_Id(Integer idMedico);
    Page<Diagnostico> findDiagnosticosByCita_Paciente_Id(Integer idPaciente, Pageable pageable);
    List<Diagnostico> findDiagnosticosByCita_Paciente_Id(Integer idPaciente);
    List<Diagnostico> searchDiagnosticoByCita_Medico_Id(Integer idMedico);

    @Query("SELECT d FROM Diagnostico d " +
            "WHERE d.cita.medico.id = :medicoId " +
            "AND d.id NOT IN (SELECT t.diagnostico.id FROM Tratamiento t WHERE t.diagnostico.id IS NOT NULL)")
    List<Diagnostico> findDiagnosticosSinTratamientoByMedicoId(@Param("medicoId") Integer medicoId);

    @Query("SELECT d.enfermedad.enfermedad FROM Diagnostico d " +
            "JOIN d.cita c " +
            "WHERE c.paciente.id = :pacienteId AND c.fecha >= :fechaInicio " +
            "ORDER BY d.enfermedad.enfermedad")
    List<String> findEnfermedadesPorFechaAnterior(@Param("pacienteId") Integer pacienteId,
                                                  @Param("fechaInicio") Date fechaInicio);

    @Query("SELECT d.enfermedad.enfermedad, COUNT(d.id) as cantidadDiagnosticos " +
            "FROM Diagnostico d " +
            "JOIN d.cita c " +
            "WHERE c.paciente.id = :pacienteId " +
            "AND c.fecha >= :fechaInicio " +
            "GROUP BY d.enfermedad.enfermedad " +
            "ORDER BY d.enfermedad.enfermedad")
    List<Object[]> obtenerCantidadDeDiagnosticosEnfermedadByPacienteId(
            @Param("fechaInicio") Date fechaInicio,
            @Param("pacienteId") Integer pacienteId);
}
