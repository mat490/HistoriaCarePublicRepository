package mx.sgahc.repository.enfermedades;

import mx.sgahc.model.enfermedades.Tratamiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Integer> {
    Tratamiento findTratamientoByDiagnostico_Id(Integer diagnosticoId);
    List<Tratamiento> findTratamientoByDiagnostico_Cita_Paciente_Id(Integer citaPacienteId);
    List<Tratamiento> findTratamientoByMedicamento_Id(Integer medicamentoId);
    Page<Tratamiento> findTratamientoByDiagnostico_Cita_Paciente_Id(Integer pacienteId, Pageable pageable);

    @Query("SELECT DISTINCT m.nombreGenerico " +
            "FROM Tratamiento t " +
            "JOIN t.medicamento m " +
            "JOIN t.diagnostico d " +
            "JOIN d.cita c " +
            "JOIN c.paciente p " +
            "WHERE p.id = :pacienteId " +
            "AND t.fechaInicio >= :fechaInicio " +
            "ORDER BY m.nombreGenerico")
    List<String> obtenerMedicamentosUltimosSeisMeses(
            @Param("fechaInicio") Date fechaInicio,
            @Param("pacienteId") Integer pacienteId
    );

    @Query("SELECT m.nombreGenerico, COUNT(t.id) as cantidadRecetada " +
            "FROM Tratamiento t " +
            "JOIN t.medicamento m " +
            "JOIN t.diagnostico d " +
            "JOIN d.cita c " +
            "WHERE c.paciente.id = :pacienteId " +
            "AND t.fechaInicio >= :fechaInicio " +
            "GROUP BY m.nombreGenerico " +
            "ORDER BY m.nombreGenerico")
    List<Object[]> obtenerCantidadMedicamentosUltimosSeisMeses(
            @Param("fechaInicio") Date fechaInicio,
            @Param("pacienteId") Integer pacienteId);


}
