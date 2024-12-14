package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.enfermedades.Enfermedad;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Antecedentes_Patologicos")
@NamedQueries(
        @NamedQuery(
                name = "AntecedentePatologico.contarPorEnfermedad",
                query = "SELECT a.enfermedad AS enfermedad, COUNT(a) AS conteo" +
                        " FROM AntecedentePatologico a GROUP BY a.enfermedad "
        )
)
public class AntecedentePatologico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Antecedente_Patologico")
    private Integer id;
    private String descripcion;
    @Column(name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_enfermedad")
    private Enfermedad enfermedad;

    public AntecedentePatologico(String descripcion, Paciente paciente, Enfermedad enfermedad) {
        this.descripcion = descripcion;
        this.paciente = paciente;
        this.enfermedad = enfermedad;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AntecedentePatologico that = (AntecedentePatologico) o;
        return Objects.equals(getPaciente(), that.getPaciente()) && Objects.equals(getEnfermedad(), that.getEnfermedad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaciente(), getEnfermedad());
    }

    @Override
    public String toString() {
        return "AntecedentePatologico{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", paciente=" + paciente +
                ", enfermedad=" + enfermedad +
                '}';
    }
}
