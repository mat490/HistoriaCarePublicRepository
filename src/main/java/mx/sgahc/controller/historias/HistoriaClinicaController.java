package mx.sgahc.controller.historias;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.service.citas.CitaService;
import mx.sgahc.service.historias.HistoriasService;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.pacientes.dto.PacienteDTOService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/historias")
public class HistoriaClinicaController {
    private final PacienteService pacienteService;
    private final PacienteDTOService pacienteDTOService;
    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;
    private final CitaService citaService;
    private final HistoriasService historiasService;

    @Autowired
    public HistoriaClinicaController(PacienteService pacienteService, PacienteDTOService pacienteDTOService, UsuarioService usuarioService, UsuarioDTOService usuarioDTOService, CitaService citaService, HistoriasService historiasService) {
        this.pacienteService = pacienteService;
        this.pacienteDTOService = pacienteDTOService;
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
        this.citaService = citaService;
        this.historiasService = historiasService;
    }

    @GetMapping("/detalles-paciente/{id}")
    public String detallesPaciente(@PathVariable("id") Integer id,
                                   Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        PacienteDTORequest pacienteR = pacienteDTOService.getPacienteDTORequest(id);
        PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponse(id);

        if (pacienteDTOService.validarAccesoPacienteDatos(usuarioLog.getId(), pacienteR)){
            List<Integer> visitasPorMesArray = historiasService.getNumeroCitasPorMesByPacienteId(paciente.getId());
            log.info("Visitas paciente: {}", visitasPorMesArray);

            List<String> medicamentosArray = historiasService.getMedicamentosUltimosSeisMeses(paciente.getId());
            List<Integer> diasDeConsumoMedArray = historiasService.getCantidadTratamientosUltimosSeisMeses(id);
            log.info("Medicamentos: {}", medicamentosArray);
            log.info("Dias de consumo: {}", medicamentosArray);

            List<String> enfermedadesArray = historiasService.getEnfermedadesUltimoAnio(paciente.getId());
            List<Integer> diagnosticosEnfermedadesArray = historiasService.getCantidadEnfermedadesUltimoAnio(paciente.getId());
            log.info("Enfermedades: {}", enfermedadesArray);
            log.info("Veces diagnosticadas: {}", diagnosticosEnfermedadesArray);


            model.addAttribute("paciente", paciente);
            model.addAttribute("visitasPorMesArray", visitasPorMesArray);
            model.addAttribute("medicamentosArray", medicamentosArray);
            model.addAttribute("diasDeConsumoMedArray", diasDeConsumoMedArray);
            model.addAttribute("enfermedadesArray", enfermedadesArray);
            model.addAttribute("diagnosticosEnfermedadesArray", diagnosticosEnfermedadesArray);

            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            model.addAttribute("tituloPagina", "Hisotria Clinica de "+paciente.getDatosPersonales());

            return "historia-clinica/historia-clinica-paciente";
        }
        return "redirect:/inicio";
    }
}
