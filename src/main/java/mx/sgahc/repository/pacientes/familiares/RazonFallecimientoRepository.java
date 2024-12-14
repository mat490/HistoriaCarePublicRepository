package mx.sgahc.repository.pacientes.familiares;

import mx.sgahc.model.pacientes.familiares.RazonFallecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RazonFallecimientoRepository extends JpaRepository<RazonFallecimiento, Integer> {
}
