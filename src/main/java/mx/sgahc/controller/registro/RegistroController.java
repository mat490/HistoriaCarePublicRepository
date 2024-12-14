package mx.sgahc.controller.registro;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.Direccion;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.*;
import mx.sgahc.model.usuarios.Rol;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.RolDTO;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.usuarios.dto.UsuarioDTORequest;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DireccionService;
import mx.sgahc.service.datos.SexoService;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.pacientes.*;
import mx.sgahc.service.usuarios.RolService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.RolDTOService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;

    private final RolService rolService;
    private final RolDTOService rolDTOService;

    @Autowired
    public RegistroController(UsuarioService usuarioService, UsuarioDTOService usuarioDTOService, RolService rolService, RolDTOService rolDTOService) {
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;

        this.rolService = rolService;
        this.rolDTOService = rolDTOService;
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        UsuarioDTORequest usuario = new UsuarioDTORequest();
        List<RolDTO> selectRol = rolDTOService.getRoles();
        model.addAttribute("usuarioNuevo", usuario);
        model.addAttribute("selectRol", selectRol);

        return "registro/registro";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("usuarioNuevo") UsuarioDTORequest usuario, Model model,
                           BindingResult result) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        List<RolDTO> selectRol = rolDTOService.getRoles();
        if (result.hasErrors()) {
            model.addAttribute("selectRol", selectRol);
            model.addAttribute("usuarioNuevo", usuario);
            model.addAttribute("usuarioLog", usuarioLog);
            model.addAttribute("errors", "Error al registrar el usuario");
            return "registro/registro";
        }
        usuario.setFechaCreacion(Date.from(Instant.now()));
        usuarioDTOService.saveUserRequest(usuario);
        model.addAttribute("success", "Cuenta creada con éxito");

        return "inicio";
    }






}
