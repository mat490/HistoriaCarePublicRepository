package mx.sgahc.repository.pacientes;

import mx.sgahc.model.pacientes.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {
}
