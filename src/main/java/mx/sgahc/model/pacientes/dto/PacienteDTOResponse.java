package mx.sgahc.model.pacientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PacienteDTOResponse {
    private Integer id;
    private String datosPersonales;
    private String telefono;
    private String correoElectronico;
    private String estadoCivil;
    private String ocupacion;
    private String combe;
    private String grupoSanguineo;
    private String rh;
    private String lugarNacimiento;
}
