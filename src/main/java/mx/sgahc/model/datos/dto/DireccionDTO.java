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
public class DireccionDTO {
    private Integer id;
    private String pais;
    private String estado;
    private String municipio;
    private String colonia;
    private String calle;
    private Integer numeroCasa;
    private String codigoPostal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DireccionDTO that = (DireccionDTO) o;
        return Objects.equals(pais, that.pais) && Objects.equals(estado, that.estado) &&
                Objects.equals(municipio, that.municipio) && Objects.equals(colonia, that.colonia)
                && Objects.equals(calle, that.calle) && Objects.equals(numeroCasa, that.numeroCasa)
                && Objects.equals(codigoPostal, that.codigoPostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pais, estado, municipio, colonia, calle, numeroCasa, codigoPostal);
    }
}
