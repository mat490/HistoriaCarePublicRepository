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
public class CombeDTO {
    private Integer id;
    private String combe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombeDTO combeDTO = (CombeDTO) o;
        return Objects.equals(combe, combeDTO.combe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(combe);
    }
}
