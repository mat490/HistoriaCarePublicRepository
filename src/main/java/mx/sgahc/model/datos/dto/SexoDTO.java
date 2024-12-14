package mx.sgahc.model.datos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SexoDTO {
    private Integer id;
    private String sexo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SexoDTO sexoDTO = (SexoDTO) o;
        return Objects.equals(sexo, sexoDTO.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sexo);
    }
}
