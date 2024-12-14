package mx.sgahc.repository.datos;

import mx.sgahc.model.datos.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

}
