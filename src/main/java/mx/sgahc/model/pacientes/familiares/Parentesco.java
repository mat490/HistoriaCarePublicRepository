package mx.sgahc.model.pacientes.familiares;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parentescos")
public class Parentesco {
    @Id
    @Column(name = "id_parentesco")
    private Integer id;
    private String parentesco;

    public Parentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parentesco that = (Parentesco) o;
        return Objects.equals(getParentesco(), that.getParentesco());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getParentesco());
    }

    @Override
    public String toString() {
        return "Parentesco{" +
                "id=" + id +
                ", parentesco='" + parentesco + '\'' +
                '}';
    }
}
