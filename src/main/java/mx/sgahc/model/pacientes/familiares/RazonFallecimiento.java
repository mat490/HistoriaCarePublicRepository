package mx.sgahc.model.pacientes.familiares;

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
@Table(name = "Razones_Fallecimiento")
public class RazonFallecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_razon_fallecimiento")
    private Integer id;
    private String razon;

    public RazonFallecimiento(String razon) {
        this.razon = razon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RazonFallecimiento that = (RazonFallecimiento) o;
        return Objects.equals(getRazon(), that.getRazon());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRazon());
    }

    @Override
    public String toString() {
        return "RazonFallecimiento{" +
                "id=" + id +
                ", razon='" + razon + '\'' +
                '}';
    }
}
