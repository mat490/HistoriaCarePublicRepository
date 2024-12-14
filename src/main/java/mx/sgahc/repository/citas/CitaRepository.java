package mx.sgahc.repository.citas;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.DatosExploracion;
import mx.sgahc.model.medicos.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByFechaAfter(Date date);
    List<Cita> findByMedico(Medico medico);
    List<DatosExploracion> findDatosPorCitaId(Integer id);
    List<Cita> citasEntreFechas(Date fechaInic, Date fechaFin);
    List<Cita> citasNoEntreFechas(Date fechaInic, Date fechaFin);

    @Query("SELECT c FROM Cita c " +
            "WHERE c.medico.id = :medicoId " +
            "AND DATE(c.fecha) = :fecha " +
            "ORDER BY c.fecha ASC")
    List<Cita> findCitasByMedicoAndFecha(
            @Param("medicoId") Integer medicoId,
            @Param("fecha") Date fecha
    );

    @Query("SELECT c FROM Cita c " +
            "WHERE c.paciente.id = :pacienteId " +
            "AND DATE(c.fecha) = :fecha " +
            "ORDER BY c.fecha ASC")
    List<Cita> findCitasByPacienteAndFecha(
            @Param("pacienteId") Integer medicoId,
            @Param("fecha") Date fecha
    );

    @Query("SELECT c FROM Cita c " +
            "WHERE c.medico.id = :medicoId " +
            "AND c.id NOT IN (SELECT d.cita.id FROM Diagnostico d)")
    List<Cita> findCitasPorMedicoSinDiagnostico(@Param("medicoId") Integer medicoId);

    @Query("SELECT c FROM Cita c " +
            "WHERE c.fecha > CURRENT_TIMESTAMP " +
            "AND c.medico.id = :medicoId " +
            "ORDER BY c.fecha")
    Page<Cita> findCitasPendientesByMedicoId(@Param("medicoId") Integer medicoId, Pageable pageable);

    @Query("SELECT c FROM Cita c " +
            "WHERE c.fecha > CURRENT_TIMESTAMP " +
            "AND c.paciente.id = :pacienteId " +
            "ORDER BY c.fecha")
    Page<Cita> findCitasPendientesByPacienteId(@Param("pacienteId") Integer pacienteId, Pageable pageable);

    @Query("SELECT FUNCTION('MONTH', c.fecha), COUNT(c) " +
            "FROM Cita c " +
            "WHERE c.paciente.id = :pacienteId " +
            "AND FUNCTION('YEAR', c.fecha) = :anio " +
            "GROUP BY FUNCTION('MONTH', c.fecha) " +
            "ORDER BY FUNCTION('MONTH', c.fecha)")
    List<Object[]> contarCitasPorMesPorPaciente(
            @Param("pacienteId") Integer pacienteId,
            @Param("anio") Integer anio);

    List<Cita> findCitaByMedico_Id(Integer id);
    List<Cita> findCitaByPaciente_Id(Integer id);


}
