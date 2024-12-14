package mx.sgahc.model.enfermedades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.citas.Diagnostico;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tratamientos")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "duracion")
    private String duracion;
    @Column(name = "notas_adicionales")
    private String notasAdicionales;
    @Column(name = "dosis")
    private String dosis;
    @Column(name = "frecuencia")
    private String frecuencia;

    @ManyToOne
    @JoinColumn(name = "id_diagnostico")
    private Diagnostico diagnostico;
    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    public Tratamiento(Date fehcaInicio, String duracion, String notasAdicionales,
                       Diagnostico diagnostico, Medicamento medicamento) {
        this.fechaInicio = fehcaInicio;
        this.duracion = duracion;
        this.notasAdicionales = notasAdicionales;
        this.diagnostico = diagnostico;
        this.medicamento = medicamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tratamiento that = (Tratamiento) o;
        return Objects.equals(getFechaInicio(), that.getFechaInicio()) && Objects.equals(getDiagnostico(),
                that.getDiagnostico()) && Objects.equals(getMedicamento(), that.getMedicamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFechaInicio(), getDiagnostico(), getMedicamento());
    }
}
