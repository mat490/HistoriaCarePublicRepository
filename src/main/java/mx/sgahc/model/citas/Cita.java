package mx.sgahc.model.citas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "citas")
@NamedQueries(
        {
                @NamedQuery(
                name = "Cita.citasEntreFechas",
                query = "SELECT c FROM Cita c WHERE c.fecha BETWEEN ?1 AND ?2 " +
                        "ORDER BY c.fecha"
                ),
                @NamedQuery(
                        name = "Cita.citasNoEntreFechas",
                        query = "SELECT c FROM Cita c WHERE c.fecha NOT BETWEEN ?1 AND ?2 " +
                                "ORDER BY c.fecha"
                ),
                @NamedQuery(
                        name = "Cita.findDatosPorCitaId",
                        query = "SELECT d FROM Cita c JOIN c.datosExploracion d WHERE c.id = ?1"
                )
        }
)
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @OneToMany(mappedBy = "cita")
    List<DatosExploracion> datosExploracion = new ArrayList<>();

    public Cita(Date fecha, Paciente paciente, Medico medico) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(getFecha(), cita.getFecha()) && Objects.equals(getPaciente(),
                cita.getPaciente()) && Objects.equals(getMedico(), cita.getMedico());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFecha(), getPaciente(), getMedico());
    }

    @Override
    public String toString() {
        return "Cita: \n"+"Fecha: "+this.getFecha()+"\n"+ getPaciente() + "\n"+getMedico();
    }
}
