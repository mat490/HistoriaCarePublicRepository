package mx.sgahc;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.usuarios.NivelPermiso;
import mx.sgahc.model.usuarios.Rol;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.repository.citas.CitaRepository;
import mx.sgahc.repository.citas.DiagnosticoRepository;
import mx.sgahc.repository.enfermedades.EnferemdadRepository;
import mx.sgahc.repository.medicos.EspecialidadRepository;
import mx.sgahc.repository.medicos.MedicoRepository;
import mx.sgahc.repository.usuarios.RolRepository;
import mx.sgahc.repository.usuarios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HistoriaCareApplicationTests {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RolRepository rolRepository;
	@Autowired
	MedicoRepository medicoRepository;
	@Autowired
	EspecialidadRepository especialidadRepository;
	@Autowired
	DiagnosticoRepository diagnosticoRepository;
	@Autowired
	CitaRepository citaRepository;
	@Autowired
	EnferemdadRepository enferemdadRepository;

	@Test
	void contextLoads() {
		Pageable pagina = PageRequest.of(0, 4,
				Sort.by("datosPersonales").ascending());

		Page<Medico> medicos = medicoRepository.buscarMedicosPorEspecialidadYEstado(
				especialidadRepository.findById(2).get(), "Jalisco", pagina
		);
		System.out.println(medicos.getTotalElements());

	}

	@Test
	void getDiagnosticoTest(){
		Cita cita = citaRepository.findById(2).orElse(null);
		Enfermedad enfermedad = enferemdadRepository.findById(2).orElse(null);

		Diagnostico diagnostico = new Diagnostico();

		diagnostico.setCita(cita);
		diagnostico.setEnfermedad(enfermedad);
		diagnostico.setTitulo("Asma");
		diagnostico.setDescripcion("Tiene Asma");
		diagnostico.setFecha(new Date());

		diagnosticoRepository.save(diagnostico);
	}

}
