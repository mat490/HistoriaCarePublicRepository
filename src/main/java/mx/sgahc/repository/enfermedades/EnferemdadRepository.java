package mx.sgahc.repository.enfermedades;

import mx.sgahc.model.enfermedades.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EnferemdadRepository extends JpaRepository<Enfermedad, Integer> {
    Enfermedad findByEnfermedad(String enfermedad);
}
