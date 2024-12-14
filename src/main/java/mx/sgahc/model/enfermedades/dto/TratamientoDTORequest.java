package mx.sgahc.model.enfermedades.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.enfermedades.Medicamento;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TratamientoDTORequest {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fechaInicio;
    private String duracion;
    private String notasAdicionales;
    private String dosis;
    private String frecuencia;
    private Integer diagnosticoId;
    private Integer medicamentoId;
}
