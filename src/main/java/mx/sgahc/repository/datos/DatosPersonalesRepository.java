package mx.sgahc.repository.datos;

import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.pacientes.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatosPersonalesRepository extends JpaRepository<DatosPersonales, Integer> {
    List<DatosPersonales> findByNombreContainingAndApellido1(String nombre, String apellido1);
    @Query("SELECT dp FROM DatosPersonales dp WHERE dp.usuario.id = :idUsuario")
    DatosPersonales findDatosPersonalesByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
