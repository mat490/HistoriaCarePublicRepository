package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rh")
public class Rh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RH")
    private Integer id;
    private String rh;

    public Rh(String rh) {
        this.rh = rh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rh rh1 = (Rh) o;
        return Objects.equals(getRh(), rh1.getRh());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRh());
    }

    @Override
    public String toString(){
        return "ID: "+getId()+" RH: "+getRh();
    }
}
