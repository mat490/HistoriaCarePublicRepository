package mx.sgahc.model.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.enfermedades.Enfermedad;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DiagnosticoDTORequest {
    private Integer id;
    private String titulo;
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;
    private Integer citaId;
    private Integer enfermedadId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagnosticoDTORequest that = (DiagnosticoDTORequest) o;
        return Objects.equals(fecha, that.fecha) && Objects.equals(citaId, that.citaId) && Objects.equals(enfermedadId, that.enfermedadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, citaId, enfermedadId);
    }
}
