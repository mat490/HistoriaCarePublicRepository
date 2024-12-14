package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Combe")
public class Combe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMBE")
    private Integer id;
    private String combe;

    public Combe(String combe) {
        this.combe = combe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combe combe1 = (Combe) o;
        return Objects.equals(getCombe(), combe1.getCombe());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCombe());
    }

    @Override
    public String toString(){
        return "ID: "+id+" COMBE: "+combe;
    }
}
