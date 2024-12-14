package mx.sgahc.repository.pacientes;

import mx.sgahc.model.pacientes.GrupoSanguineo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GrupoSanguineoRepository extends JpaRepository<GrupoSanguineo, Integer> {
}
