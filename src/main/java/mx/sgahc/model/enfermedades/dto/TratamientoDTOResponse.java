package mx.sgahc.model.enfermedades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TratamientoDTOResponse {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fechaInicio;
    private String duracion;
    private String notasAdicionales;
    private String dosis;
    private String frecuencia;
    private String diagnostico;
    private String medico;
    private String paciente;
    private Integer diagnosticoId;
    private String medicamento;
}
