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
public class MedicamentoDTOResponse {
    private Integer id;
    private String nombreGenerico;
    private String descripcion;
    private String advertencias;
}
