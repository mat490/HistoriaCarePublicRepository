package mx.sgahc.model.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioDTORequest {
    private Integer id;
    private String usuario;
    private String correoElectronico;
    private Date fechaCreacion;
    private String contrasenia;
    private Integer rolId;
}
