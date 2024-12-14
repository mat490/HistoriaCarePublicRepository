package mx.sgahc.model.pacientes.dto;


import jakarta.validation.constraints.PastOrPresent;
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
public class AntecedentePatologicoDTORequest {

    private Integer id;
    private String descripcion;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Integer pacienteId;
    private Integer enfermedadId;
}

