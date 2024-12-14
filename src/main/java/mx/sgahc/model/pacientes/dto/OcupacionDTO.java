package mx.sgahc.model.pacientes.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OcupacionDTO {
    private Integer id;
    private String ocupacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcupacionDTO that = (OcupacionDTO) o;
        return Objects.equals(ocupacion, that.ocupacion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ocupacion);
    }
}
