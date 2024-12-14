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
@Table(name = "enfermedades")
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enfermedad")
    private Integer id;
    private String enfermedad;
    private String descripcion;

    public Enfermedad(String enfermedad, String descripcion) {
        this.enfermedad = enfermedad;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enfermedad that = (Enfermedad) o;
        return Objects.equals(getEnfermedad(), that.getEnfermedad());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEnfermedad());
    }

    @Override
    public String toString() {
        return "Enfermedad{" +
                "id=" + id +
                ", enfermedad='" + enfermedad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
