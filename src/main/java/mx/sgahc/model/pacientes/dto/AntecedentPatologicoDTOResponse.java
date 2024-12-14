package mx.sgahc.model.pacientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AntecedentPatologicoDTOResponse {

    private Integer id;
    private String descripcion;
    private Date fecha;
    private String paciente;
    private String enfermedad;
}
