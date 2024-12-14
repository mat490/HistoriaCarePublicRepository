package mx.sgahc.model.datos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DatosPersonalesDTORequest {
    private Integer id;
    private String nombre;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Integer edad;
    private String telefono;
    private Integer usuarioId;
    private Integer direccionId;
    private Integer sexoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosPersonalesDTORequest that = (DatosPersonalesDTORequest) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(nombre2, that.nombre2) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(edad, that.edad) && Objects.equals(telefono, that.telefono) && Objects.equals(usuarioId, that.usuarioId) && Objects.equals(direccionId, that.direccionId) && Objects.equals(sexoId, that.sexoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, nombre2, apellido1, apellido2, fechaNacimiento, edad, telefono, usuarioId, direccionId, sexoId);
    }
}
