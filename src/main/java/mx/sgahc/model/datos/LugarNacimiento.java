package mx.sgahc.model.datos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Lugares_Nacimiento")
public class LugarNacimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Lugar_Nacimiento")
    private Integer id;
    @Column(name = "pais")
    private String pais;
    @Column(name = "estado")
    private String estado;
    @Column(name = "municipio")
    private String municipio;

    public LugarNacimiento(String pais, String estado, String municipio) {
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LugarNacimiento that = (LugarNacimiento) o;
        return Objects.equals(getPais(), that.getPais()) && Objects.equals(getEstado(), that.getEstado()) && Objects.equals(getMunicipio(), that.getMunicipio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPais(), getEstado(), getMunicipio());
    }

    @Override
    public String toString(){
        return "[ País: "+pais+". Estado: "+estado+". Municipio: "+municipio+".]";
    }

}
