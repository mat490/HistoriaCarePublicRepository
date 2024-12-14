package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "sustancias")
public class Sustancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sustancia")
    private Integer id;
    private String sustancia;
    private String descripcion;

    public Sustancia(String sustancia, String descripcion) {
        this.sustancia = sustancia;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sustancia sustancia1 = (Sustancia) o;
        return Objects.equals(getSustancia(), sustancia1.getSustancia());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSustancia());
    }

    @Override
    public String toString() {
        return "Sustancia{" +
                "id=" + id +
                ", sustancia='" + sustancia + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
