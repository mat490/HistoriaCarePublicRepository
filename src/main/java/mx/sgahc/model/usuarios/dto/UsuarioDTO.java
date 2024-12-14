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
public class UsuarioDTO {
    private Integer id;
    private String usuario;
    private String correoElectronico;
    private Date fechaCreacion;
    private String rol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(correoElectronico, that.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, correoElectronico);
    }
}
