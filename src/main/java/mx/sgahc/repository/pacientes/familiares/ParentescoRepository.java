package mx.sgahc.repository.pacientes.familiares;

import mx.sgahc.model.pacientes.familiares.Parentesco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ParentescoRepository extends JpaRepository<Parentesco, Integer> {
}
