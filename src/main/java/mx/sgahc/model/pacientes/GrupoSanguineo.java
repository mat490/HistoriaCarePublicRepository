package mx.sgahc.model.pacientes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "grupos_sanguineos")
public class GrupoSanguineo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Grupo_Sanguineo")
    private Integer id;
    @Column(name = "grupo_sanguineo", length = 2)
    private String grupoSanguineo;

    public GrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoSanguineo that = (GrupoSanguineo) o;
        return Objects.equals(getGrupoSanguineo(), that.getGrupoSanguineo());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGrupoSanguineo());
    }

    @Override
    public String toString(){
        return "ID: "+id+" Grupo sanguineo: "+grupoSanguineo;
    }
}
