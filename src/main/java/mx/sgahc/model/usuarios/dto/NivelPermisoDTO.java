package mx.sgahc.model.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NivelPermisoDTO {
    private Integer id;
    private Integer nivel;
}
