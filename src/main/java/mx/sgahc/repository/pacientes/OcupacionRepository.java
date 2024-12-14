package mx.sgahc.repository.pacientes;

import mx.sgahc.model.pacientes.Ocupacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OcupacionRepository extends JpaRepository<Ocupacion, Integer> {
}
