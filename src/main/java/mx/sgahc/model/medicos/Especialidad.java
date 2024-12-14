package mx.sgahc.model.medicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer id;
    private String especialidad;
    @Column(name = "descripcion_especialidad")
    private String descripcionEspecialidad;

    public Especialidad(String nombreEspecialidad, String descripcionEspecialidad) {
        this.especialidad = nombreEspecialidad;
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especialidad that = (Especialidad) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEspecialidad(),
                that.getEspecialidad()) && Objects.equals(getDescripcionEspecialidad(),
                that.getDescripcionEspecialidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEspecialidad(), getDescripcionEspecialidad());
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "id=" + id +
                ", nombreEspecialidad='" + especialidad + '\'' +
                ", descripcionEspecialidad='" + descripcionEspecialidad + '\'' +
                '}';
    }
}
