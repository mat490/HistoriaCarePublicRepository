package mx.sgahc.repository.pacientes;

import mx.sgahc.model.pacientes.Combe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CombeRepository extends JpaRepository<Combe, Integer> {
}
