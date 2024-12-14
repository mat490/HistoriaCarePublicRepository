package mx.sgahc.model.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer id;
    private String rol;
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "id_np")
    private NivelPermiso nivelPermiso;

    public Rol(String rol, String descripcion, NivelPermiso nivelPermiso) {
        this.rol = rol;
        this.descripcion = descripcion;
        this.nivelPermiso = nivelPermiso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol roles = (Rol) o;
        return Objects.equals(getRol(), roles.getRol());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRol());
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", rol='" + rol + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nivelPermiso=" + nivelPermiso +
                '}';
    }
}
