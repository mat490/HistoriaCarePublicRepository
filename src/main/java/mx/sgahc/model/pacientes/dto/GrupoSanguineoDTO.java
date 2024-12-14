package mx.sgahc.model.pacientes.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GrupoSanguineoDTO {
    private Integer id;
    private String grupoSanguineo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoSanguineoDTO that = (GrupoSanguineoDTO) o;
        return Objects.equals(grupoSanguineo, that.grupoSanguineo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(grupoSanguineo);
    }
}
