package mx.sgahc.service.usuarios;

import mx.sgahc.model.usuarios.Notificacion;
import mx.sgahc.model.usuarios.dto.NotificacionDTO;

import java.util.List;

public interface NotificacionService {
    Notificacion toEntity(NotificacionDTO dto);
    NotificacionDTO toDTO(Notificacion entity);
    List<Notificacion> getNotificacionesRecientes(Integer idUsuario);
    List<Notificacion> getNotificacionesCitasRecientes(Integer idUsuario);
    List<Notificacion> getNotificacionesTratamientosRecientes(Integer idUsuario);
    Boolean notificacionesVistas(List<Integer> idNotificacion);
}
