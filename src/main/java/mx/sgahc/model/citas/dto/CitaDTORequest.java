package mx.sgahc.model.citas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
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
public class CitaDTORequest {
    private Integer id;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;
    private Integer medicoId;
    private Integer pacienteId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaDTORequest that = (CitaDTORequest) o;
        return Objects.equals(fecha, that.fecha) && Objects.equals(medicoId, that.medicoId) && Objects.equals(pacienteId, that.pacienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, medicoId, pacienteId);
    }
}
