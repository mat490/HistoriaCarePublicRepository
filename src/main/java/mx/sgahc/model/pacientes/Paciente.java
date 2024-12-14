package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.*;
import mx.sgahc.model.Suscriber;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.medicos.Medico;

import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pacientes")
@NamedQueries({
        @NamedQuery(name = "Paciente.buscarCitasPorPacienteId",
                query = "SELECT c FROM Paciente p JOIN p.citas c WHERE p.id = ?1"


        ),
        @NamedQuery(
                name = "Paciente.findMedicosByPacientesId",
                query = "SELECT m FROM Paciente p JOIN p.medicos m WHERE p.id = ?1"
        )
})
public class Paciente implements Suscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Paciente")
    private Integer id;

    /*Relaciones*/

    //ID_Datos_Personales_Generales NOT NULL,
    @ManyToOne(targetEntity = DatosPersonales.class, fetch = FetchType.LAZY)
    @JoinColumn (name = "ID_Datos_Personales_Generales", nullable = false, unique = true)
    private DatosPersonales datosPersonales;

    // ID_Estado_Civil INT NOT NULL,
    @ManyToOne(targetEntity = EstadoCivil.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Estado_Civil", nullable = false)
    private EstadoCivil estadoCivil;

    // ID_Ocupacion INT NOT NULL,
    @ManyToOne(targetEntity = Ocupacion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Ocupacion", nullable = false)
    private Ocupacion ocupacion;

    // ID_COMBE INT NOT NULL,
    @ManyToOne(targetEntity = Combe.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMBE", nullable = false)
    private Combe combe;

    // ID_Grupo_Sanguineo INT NOT NULL,
    @ManyToOne(targetEntity = GrupoSanguineo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Grupo_Sanguineo", nullable = false)
    private GrupoSanguineo grupoSanguineo;

    // ID_RH INT NOT NULL,
    @ManyToOne(targetEntity = Rh.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RH", nullable = false)
    private Rh rh;

    // ID_Lugar_Nacimiento INT NOT NULL,
    @ManyToOne(targetEntity = LugarNacimiento.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Lugar_Nacimiento", nullable = false)
    private LugarNacimiento lugarNacimiento;

    @OneToMany (mappedBy = "paciente")
    List<Cita> citas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "citas",
            joinColumns = @JoinColumn(name = "id_paciente",
                    referencedColumnName = "id_paciente"),
            inverseJoinColumns = @JoinColumn(name = "id_medico",
                    referencedColumnName = "id_medico")
    )
    Set<Medico> medicos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(getDatosPersonales(), paciente.getDatosPersonales());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDatosPersonales());
    }

    @Override
    public String toString(){
        return "Nombre: "+ getDatosPersonales().getNombre() +" "
                + getDatosPersonales().getApellido1() + " Correo: "+ getDatosPersonales().getUsuario().getCorreoElectronico();
    }
}
