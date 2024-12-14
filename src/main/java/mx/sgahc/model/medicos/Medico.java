package mx.sgahc.model.medicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.Suscriber;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.pacientes.Paciente;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "medicos")
@NamedQueries({
        @NamedQuery(name = "Medico.buscarCitasPorMedicoId",
                query = "SELECT c FROM Medico m JOIN m.citas c WHERE m.id = ?1"


        ),
        @NamedQuery(
                name = "Medico.buscarPacientesPorMedicoId",
                query = "SELECT p FROM Medico m JOIN m.pacientes p WHERE m.id = ?1"
        )
})
public class Medico implements Suscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer id;
    private String cedula;

    @ManyToOne(targetEntity = DatosPersonales.class, fetch = FetchType.LAZY)
    @JoinColumn (name = "id_datos_personales_generales", nullable = false, unique = true)
//    @Column(name = "id_datos_personales_generales")
    private DatosPersonales datosPersonales;
    @ManyToOne(targetEntity = Especialidad.class)
    @JoinColumn(name = "id_especialidad")
//    @Column(name = "id_especialidad")
    private Especialidad especialidad;

    @OneToMany (mappedBy = "medico")
    Set<Cita> citas = new HashSet<>();

    @ManyToMany(mappedBy = "medicos")
    Set<Paciente> pacientes = new HashSet<>();

    public Medico(String cedula, DatosPersonales datosPersonales, Especialidad especialidad) {
        this.cedula = cedula;
        this.datosPersonales = datosPersonales;
        this.especialidad = especialidad;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(getId(), medico.getId()) && Objects.equals(getCedula(), medico.getCedula()) && Objects.equals(getDatosPersonales(), medico.getDatosPersonales()) && Objects.equals(getEspecialidad(), medico.getEspecialidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCedula(), getDatosPersonales(), getEspecialidad());
    }

    @Override
    public String toString() {
        return "' Nombre: " + datosPersonales.getNombre() +" "+datosPersonales.getApellido1() +
                " - " + especialidad.getEspecialidad()+
                "Cedula: '" + cedula+"'";
    }


}
