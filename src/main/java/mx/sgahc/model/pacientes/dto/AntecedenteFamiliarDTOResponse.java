package mx.sgahc.model.pacientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.familiares.Parentesco;
import mx.sgahc.model.pacientes.familiares.RazonFallecimiento;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AntecedenteFamiliarDTOResponse {
    private Integer id;
    private Integer edad;
    private String paciente;
    private String lugarNacimiento;
    private String parentesco;
    private String sexo;
    private String enfermedad;
    private String razonFallecimiento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AntecedenteFamiliarDTOResponse that = (AntecedenteFamiliarDTOResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(edad, that.edad) && Objects.equals(paciente, that.paciente) && Objects.equals(lugarNacimiento, that.lugarNacimiento) && Objects.equals(parentesco, that.parentesco) && Objects.equals(sexo, that.sexo) && Objects.equals(enfermedad, that.enfermedad) && Objects.equals(razonFallecimiento, that.razonFallecimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edad, paciente, lugarNacimiento, parentesco, sexo, enfermedad, razonFallecimiento);
    }
}
