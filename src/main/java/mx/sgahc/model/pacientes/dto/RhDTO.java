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
public class RhDTO {
    private Integer id;
    private String rh;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RhDTO rhDTO = (RhDTO) o;
        return Objects.equals(rh, rhDTO.rh);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rh);
    }
}
