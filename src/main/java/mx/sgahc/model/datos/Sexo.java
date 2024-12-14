package mx.sgahc.model.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sexos")
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexo")
    private Integer id;
    private String sexo;

    public Sexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sexo sexo = (Sexo) o;
        return Objects.equals(getId(), sexo.getId()) && Objects.equals(getSexo(), sexo.getSexo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSexo());
    }

    @Override
    public String toString() {
        return "Sexo{" +
                ", nombreSexo='" + sexo + '\'' +
                '}';
    }
}
