package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "estados_civiles")
public class EstadoCivil {
    @Id
    @Column(name = "id_estado_civil")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "estado_civil")
    private String estadoCivil;

    public EstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoCivil that = (EstadoCivil) o;
        return Objects.equals(getEstadoCivil(), that.getEstadoCivil());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEstadoCivil());
    }

    @Override
    public String toString(){
        return  "ID: "+getId() +  " Estado civil: "+getEstadoCivil();
    }
}
