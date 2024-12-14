package mx.sgahc.model.citas.dto;

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
public class CitaDTOResponse {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;
    private String paciente;
    private String medico;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaDTOResponse that = (CitaDTOResponse) o;
        return Objects.equals(fecha, that.fecha) && Objects.equals(paciente, that.paciente) && Objects.equals(medico, that.medico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, paciente, medico);
    }

    @Override
    public String toString() {
        return "Paciente: "+paciente+", Medico: "+medico+", Fecha: "+fecha;
    }
}
