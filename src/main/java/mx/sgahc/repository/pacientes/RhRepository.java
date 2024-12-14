package mx.sgahc.repository.pacientes;


import mx.sgahc.model.pacientes.Rh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RhRepository extends JpaRepository<Rh, Integer> {
}
