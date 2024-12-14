package mx.sgahc.repository.usuarios;

import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);


    List<DatosPersonales> buscarDatosPorUsuarioId(Integer id);
}
