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
public class AntecedenteFamiliarDTORequest {
    private Integer id;
    private Integer edad;
    private Integer pacienteId;
    private Integer lugarNacimientoId;
    private Integer parentescoId;
    private Integer sexoId;
    private Integer enfermedadId;
    private Integer razonFallecimientoId;
}
