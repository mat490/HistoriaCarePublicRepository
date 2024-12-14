package mx.sgahc.model.pacientes.familiares;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.pacientes.Paciente;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Antecedentes_Heredofamiliares")
public class AntecedenteFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Antecedente_Familiar ")
    private Integer id;
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_lugar_nacimiento")
    private LugarNacimiento lugarNacimiento;
    @ManyToOne
    @JoinColumn(name = "id_parentesco")
    private Parentesco parentesco;
    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;
    @ManyToOne
    @JoinColumn(name = "id_enfermedad")
    private Enfermedad enfermedad;
    @ManyToOne
    @JoinColumn(name = "id_razon_fallecimiento")
    private RazonFallecimiento razonFallecimiento;

    public AntecedenteFamiliar(Integer edad, Paciente paciente, LugarNacimiento lugarNacimiento, Parentesco parentesco,
                               Sexo sexo, Enfermedad enfermedad, RazonFallecimiento razonFallecimiento) {
        this.edad = edad;
        this.paciente = paciente;
        this.lugarNacimiento = lugarNacimiento;
        this.parentesco = parentesco;
        this.sexo = sexo;
        this.enfermedad = enfermedad;
        this.razonFallecimiento = razonFallecimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AntecedenteFamiliar that = (AntecedenteFamiliar) o;
        return Objects.equals(getEdad(), that.getEdad()) && Objects.equals(getPaciente(),
                that.getPaciente()) && Objects.equals(getLugarNacimiento(),
                that.getLugarNacimiento()) && Objects.equals(getParentesco(),
                that.getParentesco()) && Objects.equals(getSexo(),
                that.getSexo()) && Objects.equals(getEnfermedad(),
                that.getEnfermedad()) && Objects.equals(getRazonFallecimiento(), that.getRazonFallecimiento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEdad(), getPaciente(), getLugarNacimiento(),
                getParentesco(), getSexo(), getEnfermedad(), getRazonFallecimiento());
    }

    @Override
    public String toString() {
        return "AntecedenteFamiliar{" +
                "id=" + id +
                ", edad=" + edad +
                ", paciente=" + paciente +
                ", lugarNacimiento=" + lugarNacimiento +
                ", parentesco=" + parentesco +
                ", sexo=" + sexo +
                ", enfermedad=" + enfermedad +
                ", razonFallecimiento=" + razonFallecimiento +
                '}';
    }
}
