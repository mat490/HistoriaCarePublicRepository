package mx.sgahc.repository.usuarios;

import mx.sgahc.model.usuarios.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface NorificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findNotificacionByUsuario_Id(Integer usuarioId);

    @Query("SELECT n FROM Notificacion n WHERE n.usuario.id = :usuarioID " +
            " AND n.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Notificacion> findNotificacionByUsuario_IdAndFecha(Integer usuarioID, Date fechaInicio, Date fechaFin);

    @Query("SELECT n FROM Notificacion n WHERE n.usuario.id = :usuarioID " +
            "AND n.fecha BETWEEN :fechaInicio AND :fechaFIn AND n.tipo = :tipo")
    List<Notificacion> findNotificacionByUsuario_IdAndFechaAndTipo(Integer usuarioID, Date fechaInicio, Date fechaFin,
                                                                   String tipo);

    @Modifying
    @Query("UPDATE Notificacion n SET n.estado = 'Visto' WHERE n.id IN :idsNotificaciones")
    void marcarNotificacionesComoVistas(@Param("idsNotificaciones") List<Integer> idsNotificaciones);
}
