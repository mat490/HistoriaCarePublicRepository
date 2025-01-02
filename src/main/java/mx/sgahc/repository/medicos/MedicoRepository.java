package mx.sgahc.repository.medicos;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByEspecialidad(Especialidad especialidad);
     Page<Cita> buscarCitasPorMedicoId(Integer id, Pageable pageable);
     Page<Paciente>buscarPacientesPorMedicoId( Integer id, Pageable pageable);
     List<Paciente>buscarPacientesPorMedicoId( Integer id);

     @Query("SELECT m FROM Medico m WHERE m.datosPersonales.id = :datosPersonalesId")
    Optional<Medico> findByDatosPersonalesId(@Param("datosPersonalesId") Integer datosPersonalesId);

     @Query("SELECT m FROM Medico m WHERE m.datosPersonales.usuario.id = :usuarioId")
    Optional<Medico> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT m FROM Medico m WHERE m.especialidad = ?1 AND m.datosPersonales.direccion.estado = ?2")
    Page<Medico> buscarMedicosPorEspecialidadYEstado(Especialidad especialidad, String estado, Pageable pageable);

    @Query("SELECT m FROM Medico m WHERE m.especialidad.id = ?1 AND m.datosPersonales.direccion.estado = ?2")
    List<Medico> buscarMedicosPorEspecialidadYEstadoList(Integer especialidad, String estado, Pageable pageable);

    @Query("SELECT COUNT(c.id) " +
            "FROM Medico m " +
            "JOIN m.citas c " +
            "WHERE m.id = :id AND c.fecha BETWEEN :fechaInicio AND :fechaFin")
    Integer obtenerPacientesAtendidosUltimoMesPorMedicoId(@Param("id") Integer id,
                                                          @Param("fechaInicio") Date fechaInicio,
                                                          @Param("fechaFin") Date fechaFin);



}
