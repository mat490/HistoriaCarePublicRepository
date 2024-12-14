package mx.sgahc.repository.enfermedades;

import mx.sgahc.model.enfermedades.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

}
