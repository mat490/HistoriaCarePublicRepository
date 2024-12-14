package mx.sgahc.model.medicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MedicoDTO {

    private Integer id;
    private String cedula;
    private DatosPersonalesDTO datosPersonales;
    private EspecialidadDTO especialidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicoDTO medicoDTO = (MedicoDTO) o;
        return Objects.equals(cedula, medicoDTO.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cedula);
    }

    @Override
    public String toString() {
        return this.datosPersonales.getNombre() + " " + this.datosPersonales.getApellido1() + " | Cedula: "
                + this.cedula +" | "+ this.especialidad.getEspecialidad() + " | "+ this.datosPersonales.getUsuario().getCorreoElectronico();
    }
}
