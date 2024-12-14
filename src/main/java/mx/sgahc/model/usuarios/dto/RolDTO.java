package mx.sgahc.model.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RolDTO {
    private Integer id;
    private String rol;
    private String descripcion;
    private NivelPermisoDTO nivelPermiso;
}
