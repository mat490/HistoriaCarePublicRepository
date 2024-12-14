package mx.sgahc.model.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Niveles_Permisos")
public class NivelPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_np")
    private Integer id;
    private Integer nivel;

    public NivelPermiso(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NivelPermiso that = (NivelPermiso) o;
        return Objects.equals(getNivel(), that.getNivel());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNivel());
    }

    @Override
    public String toString() {
        return "NivelPermiso{" +
                "id=" + id +
                ", nivel=" + nivel +
                '}';
    }
}
