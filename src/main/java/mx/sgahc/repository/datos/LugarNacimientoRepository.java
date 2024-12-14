package mx.sgahc.repository.datos;

import mx.sgahc.model.datos.LugarNacimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LugarNacimientoRepository extends JpaRepository<LugarNacimiento, Integer> {
}
