package mx.sgahc.model.enfermedades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EnfermedadDTOResponse {
    private Integer id;
    private String enfermedad;
    private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnfermedadDTOResponse that = (EnfermedadDTOResponse) o;
        return Objects.equals(enfermedad, that.enfermedad);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(enfermedad);
    }
}
