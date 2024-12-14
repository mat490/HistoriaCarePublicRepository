package mx.sgahc.model.pacientes.familiares;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.pacientes.Paciente;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tutores")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "ID_Datos_Personales_Generales ")
    private DatosPersonales datosPersonales;
    @ManyToOne
    @JoinColumn(name = "id_parentesco")
    private Parentesco parentesco;

    public Tutor(Paciente paciente, DatosPersonales datosPersonales, Parentesco parentesco) {
        this.paciente = paciente;
        this.datosPersonales = datosPersonales;
        this.parentesco = parentesco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(getPaciente(), tutor.getPaciente()) && Objects.equals(getDatosPersonales(),
                tutor.getDatosPersonales()) && Objects.equals(getParentesco(), tutor.getParentesco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaciente(), getDatosPersonales(), getParentesco());
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", datosPersonales=" + datosPersonales +
                ", parentesco=" + parentesco +
                '}';
    }
}
