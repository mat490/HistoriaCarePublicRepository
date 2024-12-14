package mx.sgahc.model.pacientes;

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
@Table(name = "consumo_sustancias")
public class ConsumoSustancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo_sustancia")
    private Integer id;
    @Column(name = "cantidad_consumo")
    private Integer cantidad;
    private Integer frecuencia;

    @ManyToOne
    @JoinColumn(name = "id_paciente ")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_sustancia")
    private Sustancia sustancia;

    public ConsumoSustancia(Integer cantidad, Integer frecuencia, Paciente paciente, Sustancia sustancia) {
        this.cantidad = cantidad;
        this.frecuencia = frecuencia;
        this.paciente = paciente;
        this.sustancia = sustancia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumoSustancia that = (ConsumoSustancia) o;
        return Objects.equals(getPaciente(), that.getPaciente()) && Objects.equals(getSustancia(), that.getSustancia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaciente(), getSustancia());
    }

    @Override
    public String toString() {
        return "ConsumoSustancia{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", frecuencia=" + frecuencia +
                ", paciente=" + paciente +
                ", sustancia=" + sustancia +
                '}';
    }
}
