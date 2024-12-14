package mx.sgahc.model.citas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Datos_Exploraciones_Fisica")
public class DatosExploracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_exploracion")
    private Integer id;
    @Column(name = "habitus_exterior")
    private String habitusExterior;
    @Column(name = "temperatura_corporal")
    private Double tempCorporal;
    @Column(name = "tension_arterial")
    private String tensionArterial;
    @Column(name = "frecuencia_cardiaca")
    private Integer frecCardiaca;
    @Column(name = "frecuencia_respiratoria")
    private Integer frecRespiratoria;
    private Double peso;
    private Double talla;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;

    public DatosExploracion(String habitusExterior, Double tempCorporal, String tensionArterial, Integer frecCardiaca,
                            Integer frecRespiratoria, Double peso, Double talla, Cita cita) {
        this.habitusExterior = habitusExterior;
        this.tempCorporal = tempCorporal;
        this.tensionArterial = tensionArterial;
        this.frecCardiaca = frecCardiaca;
        this.frecRespiratoria = frecRespiratoria;
        this.peso = peso;
        this.talla = talla;
        this.cita = cita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosExploracion that = (DatosExploracion) o;
        return Objects.equals(getCita(), that.getCita());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCita());
    }

    @Override
    public String toString() {
        return "DatosExploracion{" +
                "id=" + id +
                ", habitusExterior='" + habitusExterior + '\'' +
                ", tempCorporal=" + tempCorporal +
                ", tensionArterial='" + tensionArterial + '\'' +
                ", frecCardiaca=" + frecCardiaca +
                ", frecRespiratoria=" + frecRespiratoria +
                ", peso=" + peso +
                ", talla=" + talla +
                ", cita=" + cita +
                '}';
    }
}
