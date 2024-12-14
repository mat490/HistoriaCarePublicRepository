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
public class MedicoDTOFiltroEstado {
    private Integer id;
    private String cedula;
    private Integer especialidadId;
    private String estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicoDTOFiltroEstado that = (MedicoDTOFiltroEstado) o;
        return Objects.equals(cedula, that.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cedula);
    }
}
