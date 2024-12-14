package mx.sgahc.model.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.datos.DatosPersonales;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Usuarios")
@NamedQueries({
        @NamedQuery(name = "Usuario.buscarDatosPorUsuarioId",
                query = "SELECT d FROM Usuario u JOIN u.datosPersonales d WHERE u.id = ?1"


        )
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;
    private String usuario;
    private String contrasenia;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "fecha_creacion_usuario")
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private Set<DatosPersonales> datosPersonales = new HashSet<>();


    public Usuario(String usuario, String correoElectronico, String contrasenia, Rol rol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getCorreoElectronico(), usuario.getCorreoElectronico());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCorreoElectronico());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", fechacreacion=" + fechaCreacion +
                ", rol=" + rol +
                '}';
    }
}
