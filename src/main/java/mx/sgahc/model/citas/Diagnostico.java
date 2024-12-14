package mx.sgahc.model.citas;

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
@Table(name = "Diagnosticos")
@NamedQueries(
        @NamedQuery(
                name = "Diagnostico.contarPorEnfermedad",
                query = "SELECT d.enfermedad AS enfermedad, COUNT(d) AS conteo FROM Diagnostico d GROUP BY d.enfermedad "+
                        "ORDER BY 1 "
        )
)
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;
    @ManyToOne
    @JoinColumn(name = "id_enfermedad")
    private Enfermedad enfermedad;

    public Diagnostico(String titulo, String descripcion, Date fecha, Cita cita, Enfermedad enfermedad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cita = cita;
        this.enfermedad = enfermedad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnostico that = (Diagnostico) o;
        return Objects.equals(getFecha(), that.getFecha()) && Objects.equals(getCita(),
                that.getCita()) && Objects.equals(getEnfermedad(), that.getEnfermedad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFecha(), getCita(), getEnfermedad());
    }

    @Override
    public String toString() {
        return "Diagnostico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", cita=" + cita +
                ", enfermedad=" + enfermedad +
                '}';
    }
}
