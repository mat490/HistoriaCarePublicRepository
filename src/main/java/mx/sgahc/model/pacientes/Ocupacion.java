package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ocupaciones")
public class Ocupacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ocupacion")
    private Integer id;
    private String ocupacion;

    public Ocupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ocupacion that = (Ocupacion) o;
        return Objects.equals(getOcupacion(), that.getOcupacion());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOcupacion());
    }

    @Override
    public String toString(){
        return "ID: "+ocupacion+" Ocupación: "+ocupacion;
    }
}
