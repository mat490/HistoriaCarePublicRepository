package mx.sgahc.model.pacientes.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;
import mx.sgahc.model.pacientes.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PacienteDTORequest {
    private Integer id;
    private Integer datosPersonalesId;
    private Integer estadoCivilId;
    private Integer ocupacionId;
    private Integer combeId;
    private Integer grupoSanguineoId;
    private Integer rhId;
    private LugarNacimientoDTO lugarNacimiento;
}
