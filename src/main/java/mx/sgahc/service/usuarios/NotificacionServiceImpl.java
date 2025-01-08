package mx.sgahc.service.usuarios;

import mx.sgahc.model.usuarios.Notificacion;
import mx.sgahc.model.usuarios.dto.NotificacionDTO;
import mx.sgahc.repository.usuarios.NorificacionRepository;
import mx.sgahc.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    private final NorificacionRepository norificacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public NotificacionServiceImpl(NorificacionRepository norificacionRepository, UsuarioRepository usuarioRepository) {
        this.norificacionRepository = norificacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Notificacion toEntity(NotificacionDTO dto) {
        if (dto != null) {
            Notificacion notificacion = new Notificacion();
            if (dto.getId() != null)
                notificacion.setId(dto.getId());
            if (dto.getNotificacion() != null)
                notificacion.setNotificacion(dto.getNotificacion());
            if (dto.getUsuario() != null)
                notificacion.setUsuario(
                        usuarioRepository.findByCorreoElectronico(dto.getUsuario()).orElse(null)
                );
            if (dto.getTipo() != null)
                notificacion.setTipo(dto.getTipo());
            if (dto.getFecha() != null)
                notificacion.setFecha(dto.getFecha());
            return notificacion;
        }
        return null;
    }

    @Override
    public NotificacionDTO toDTO(Notificacion entity) {
        if (entity != null) {
            NotificacionDTO dto = new NotificacionDTO();
            if (entity.getId() != null)
                dto.setId(entity.getId());
            if (entity.getNotificacion() != null)
                dto.setNotificacion(entity.getNotificacion());
            if (entity.getUsuario() != null)
                dto.setUsuario(entity.getUsuario().getCorreoElectronico());
            if (entity.getTipo() != null)
                dto.setTipo(entity.getTipo());
            if (entity.getFecha() != null)
                dto.setFecha(entity.getFecha());
            return dto;
        }
        return null;
    }

    @Override
    public List<Notificacion> getNotificacionesRecientes(Integer idUsuario) {
        Date fechaInicio = Date.from(Instant.now());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date fechaFinal = calendar.getTime();

        return norificacionRepository.findNotificacionByUsuario_IdAndFecha(idUsuario, fechaInicio, fechaFinal);
    }

    @Override
    public List<Notificacion> getNotificacionesCitasRecientes(Integer idUsuario) {
        Date fechaInicio = Date.from(Instant.now());
        String tipo = "Cita";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date fechaFinal = calendar.getTime();
        return norificacionRepository.findNotificacionByUsuario_IdAndFechaAndTipo(idUsuario, fechaInicio, fechaFinal, tipo);
    }

    @Override
    public List<Notificacion> getNotificacionesTratamientosRecientes(Integer idUsuario) {
        Date fechaInicio = Date.from(Instant.now());
        String tipo = "Tratamiento";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date fechaFinal = calendar.getTime();
        return norificacionRepository.findNotificacionByUsuario_IdAndFechaAndTipo(idUsuario, fechaInicio, fechaFinal, tipo);
    }

    @Override
    public Boolean notificacionesVistas(List<Integer> idNotificaciones) {
        if (idNotificaciones != null) {
            norificacionRepository.marcarNotificacionesComoVistas(idNotificaciones);
            return true;
        }
        return false;
    }
}
