package mx.sgahc.model.enfermedades;

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
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Integer id;
    @Column(name = "nombre_generico")
    private String nombreGenerico;
    @Column(name = "descripcion_medicamento")
    private String descripcion;
    private String advertencias;

    public Medicamento(String nombreGenerico, String descripcion, String advertencias) {
        this.nombreGenerico = nombreGenerico;
        this.descripcion = descripcion;
        this.advertencias = advertencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicamento that = (Medicamento) o;
        return Objects.equals(getNombreGenerico(), that.getNombreGenerico());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNombreGenerico());
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", nombreGenerico='" + nombreGenerico + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", advertencias='" + advertencias + '\'' +
                '}';
    }
}
