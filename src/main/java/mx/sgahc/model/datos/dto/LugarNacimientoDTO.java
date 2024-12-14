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
public class LugarNacimientoDTO {
    private Integer id;
    private String pais;
    private String estado;
    private String municipio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LugarNacimientoDTO that = (LugarNacimientoDTO) o;
        return Objects.equals(pais, that.pais) && Objects.equals(estado, that.estado) && Objects.equals(municipio, that.municipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pais, estado, municipio);
    }
}
