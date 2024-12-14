package mx.sgahc.model.enfermedades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tratamientos_Familiares")
public class TratamientoFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento_familiar")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Antecedente_Familiar")
    private AntecedenteFamiliar antecedenteFamiliar;
    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    public TratamientoFamiliar(AntecedenteFamiliar antecedenteFamiliar, Medicamento medicamento) {
        this.antecedenteFamiliar = antecedenteFamiliar;
        this.medicamento = medicamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TratamientoFamiliar that = (TratamientoFamiliar) o;
        return Objects.equals(getAntecedenteFamiliar(), that.getAntecedenteFamiliar()) && Objects.equals(getMedicamento(), that.getMedicamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAntecedenteFamiliar(), getMedicamento());
    }

    @Override
    public String toString() {
        return "TratamientoFamiliar{" +
                "id=" + id +
                ", antecedenteFamiliar=" + antecedenteFamiliar +
                ", medicamento=" + medicamento +
                '}';
    }
}
