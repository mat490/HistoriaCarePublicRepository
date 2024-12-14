package mx.sgahc.model.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.usuarios.Usuario;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "datos_personales_generales")
public class DatosPersonales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_personales_generales")
    private Integer id;
    private String nombre;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Integer edad;
    private String telefono;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(targetEntity = Direccion.class)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;
    @ManyToOne(targetEntity = Sexo.class)
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    public DatosPersonales(String nombre, String nombre2, String apellido1, String apellido2, Date fechaNacimiento,
                           Integer edad, String telefono, String email, Direccion direccion, Sexo sexo) {
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosPersonales that = (DatosPersonales) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNombre(),
                that.getNombre()) && Objects.equals(getNombre2(),
                that.getNombre2()) && Objects.equals(getApellido1(),
                that.getApellido1()) && Objects.equals(getApellido2(),
                that.getApellido2()) && Objects.equals(getFechaNacimiento(),
                that.getFechaNacimiento()) && Objects.equals(getEdad(),
                that.getEdad()) && Objects.equals(getTelefono(),
                that.getDireccion()) && Objects.equals(getSexo(), that.getSexo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getNombre2(), getApellido1(), getApellido2(), getFechaNacimiento(),
                getEdad(), getTelefono(), getDireccion(), getSexo());
    }

}
