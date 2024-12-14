package mx.sgahc.model.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DiagnosticoDTOResponse {
    private Integer id;
    private String titulo;
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;
    private String cita;
    private String medico;
    private String paciente;
    private String enfermedad;
}
