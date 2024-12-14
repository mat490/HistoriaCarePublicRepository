package mx.sgahc.model.medicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EspecialidadDTO {
    private Integer id;
    private String especialidad;
    private String descripcionEspecialidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecialidadDTO that = (EspecialidadDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEspecialidad(),
                that.getEspecialidad()) && Objects.equals(getDescripcionEspecialidad(),
                that.getDescripcionEspecialidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEspecialidad(), getDescripcionEspecialidad());
    }

    @Override
    public String toString() {
        return especialidad;
    }
}
