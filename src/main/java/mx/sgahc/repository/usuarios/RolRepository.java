package mx.sgahc.repository.usuarios;

import mx.sgahc.model.usuarios.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findRolByRol(String rol);
}
