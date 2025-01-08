package mx.sgahc.model.usuarios.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificacionDTO {
    private Integer id;
    private String tipo;
    private String notificacion;
    private String estado;
    private Date fecha;
    private String usuario;
}
