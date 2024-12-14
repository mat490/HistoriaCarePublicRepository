package mx.sgahc.model.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer id;
    private String pais;
    private String estado;
    private String municipio;
    private String colonia;
    private String calle;
    @Column(name = "numero_casa")
    private Integer numeroCasa;
    @Column(name = "codigo_postal")
    private String codigoPostal;

    public Direccion(Integer id, String pais, String estado, String municipio, String colonia, String calle, Integer numeroCasa, String codigoPostal) {
        this.id = id;
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
    }

    public Direccion(String pais, String estado, String municipio, String colonia, String calle,
                     Integer numeroCasa, String codigoPostal) {
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(getId(), direccion.getId()) && Objects.equals(getPais(), direccion.getPais()) && Objects.equals(getEstado(), direccion.getEstado()) && Objects.equals(getMunicipio(), direccion.getMunicipio()) && Objects.equals(getColonia(), direccion.getColonia()) && Objects.equals(getCalle(), direccion.getCalle()) && Objects.equals(getCodigoPostal(), direccion.getCodigoPostal()) && Objects.equals(getNumeroCasa(), direccion.getNumeroCasa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPais(), getEstado(), getMunicipio(), getColonia(), getCalle(), getCodigoPostal(), getNumeroCasa());
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                ", municipio='" + municipio + '\'' +
                ", colonia='" + colonia + '\'' +
                ", calle='" + calle + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", numeroCasa=" + numeroCasa +
                '}';
    }
}
