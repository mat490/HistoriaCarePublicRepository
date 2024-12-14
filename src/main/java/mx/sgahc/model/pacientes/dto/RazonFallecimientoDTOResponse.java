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
public class RazonFallecimientoDTOResponse {
    private Integer id;
    private String razon;
}
