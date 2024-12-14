package mx.sgahc.repository.medicos;

import mx.sgahc.model.medicos.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
}
