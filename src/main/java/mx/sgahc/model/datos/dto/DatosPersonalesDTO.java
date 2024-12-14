package mx.sgahc.model.datos.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
public class DatosPersonalesDTO {
    private Integer id;
    private String nombre;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Integer edad;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String telefono;
    private UsuarioDTO usuario;
    private DireccionDTO direccion;
    private SexoDTO sexo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosPersonalesDTO that = (DatosPersonalesDTO) o;
        return Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(usuario);
    }
}
