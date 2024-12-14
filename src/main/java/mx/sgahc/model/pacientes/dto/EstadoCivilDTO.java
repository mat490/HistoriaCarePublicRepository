package mx.sgahc.model.pacientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EstadoCivilDTO {
    private Integer id;
    private String estadoCivil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoCivilDTO that = (EstadoCivilDTO) o;
        return Objects.equals(estadoCivil, that.estadoCivil);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(estadoCivil);
    }
}
