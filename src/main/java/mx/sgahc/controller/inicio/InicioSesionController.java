package mx.sgahc.controller.inicio;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.security.jwt.JWTTokenProvider;
import mx.sgahc.security.request.JwtRequest;
import mx.sgahc.security.request.LoginUserRequest;
import mx.sgahc.security.service.CustomUserDetailsService;
import mx.sgahc.service.citas.CitaService;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.medicos.MedicoServiceImpl;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
@Slf4j
@Controller
public class InicioSesionController {
    @Value("${spring.application.name}")
    String nombreApp;
   
    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;
    private final CitaService citaService;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public InicioSesionController(UsuarioService usuarioService, UsuarioDTOService usuarioDTOService, CitaService citaService, MedicoService medicoService,
                                  PacienteService pacienteService, AuthenticationManager authenticationManager,
                                  JWTTokenProvider tokenProvider) {
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
        this.citaService = citaService;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = tokenProvider;
    }

    @GetMapping("/")
    public String inicioPagina(@RequestParam(value = "error", required = false) String error, Model model) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("tituloPagina", nombreApp);
        model.addAttribute("fecha", format.format(new Date()));
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser")
        {
            return "redirect:/inicio";
        }
        if (error != null) {
            model.addAttribute("error", "Correo y/o contraseña incorrectos");
        }
        return "inicio"; // Página de login
    }

    @GetMapping("/inicio")
    public String inicioPrincipal(Model model) throws ParseException {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
        {
            log.info("Principal "+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            log.info("Authorities "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        }
        // Recupera el usuario autenticado
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);


        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        String fechaFormateada = formato.format(new Date());

        Date fechaSinHora = formato.parse(fechaFormateada);

        System.out.println("Fecha actual en formato Date (sin hora): " + fechaSinHora);
        
        DatosPersonales datosPersonales;
        Integer id = usuarioService.findByEmail(usuarioLog.getCorreoElectronico()).getId();
        
        System.out.println(usuarioService.usuarioTieneDatosPersonales(id));
        boolean registroCompleto;
        if (!usuarioService.usuarioTieneDatosPersonales(id)){
            model.addAttribute("registroIncompleto", true);
            registroCompleto = false;

        } else {
            model.addAttribute("registroIncompleto", false);
            registroCompleto = true;
        }

        if (usuarioLog.getRol().equals("Medico")){
            model.addAttribute("pacientesMedicosTxt","Pacientes");
            if (registroCompleto){

                Medico medico = medicoService.getMedicoByUsuarioId(usuarioLog.getId());
                if (medico != null){
                    model.addAttribute("citasHoy",
                            citaService.getCitasByMedicoAndFecha(
                                    medico.getId(),
                                    fechaSinHora));
                }

            }


        }
        System.out.println(registroCompleto);
        if (usuarioLog.getRol().equals("Paciente") ){
            model.addAttribute("pacientesMedicosTxt","Medicos");
            if (registroCompleto){
                Paciente paciente = pacienteService.getPacienteByUsuarioId(usuarioLog.getId());
                if (paciente != null) {
                    model.addAttribute("citasHoy",
                            citaService.getCitasByPacienteAndFecha(
                                    paciente.getId(),
                                    fechaSinHora));
                    model.addAttribute("paciente", paciente);
                }
            }


        }
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
        model.addAttribute("usuario", usuarioLog);
        model.addAttribute("tituloPagina", usuarioLog.getUsuario());


        return "dashboard";
    }

    @PostMapping("inicio/inicio-sesion")
    public String inicioSesion(@ModelAttribute LoginUserRequest loginUserRequest,
                               Model model,
                               HttpSession session,
                               HttpServletResponse res,
                               RedirectAttributes flash) {
        log.info(loginUserRequest.getUsername() +" " +loginUserRequest.getPassword());
        try {
            // Autenticamos al usuario usando el AuthenticationManager
            Authentication authentication = authenticate(loginUserRequest.getUsername(), loginUserRequest.getPassword());
            log.info("authentication {}", authentication);

            // Obtenemos el usuario autenticado
            log.info("Principal auth " + authentication.getPrincipal().toString());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Usuario usuarioLog = usuarioService.findByEmail(userDetails.getUsername());

            // Generamos el token JWT
            String jwtToken = jwtTokenProvider.generateJwtToken(authentication, usuarioLog);
            JwtRequest jwtRequest = new JwtRequest(jwtToken, usuarioLog.getId(), usuarioLog.getCorreoElectronico(),
                    jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
            log.info("Generated JWT Token: {}", jwtToken);

            // Creamos la cookie para el JWT
            Cookie cookie = new Cookie("token", jwtToken);
            cookie.setMaxAge(Integer.MAX_VALUE);  // Establecemos una vida útil muy larga para la cookie
            cookie.setPath("/");
            res.addCookie(cookie);

            // Establecemos un mensaje de éxito en la sesión
            session.setAttribute("msg", "Login OK!");

            // Añadimos atributos a la redirección
            flash.addFlashAttribute("usuarioNombre", usuarioLog.getUsuario());
            flash.addFlashAttribute("usuario", usuarioLog);
            flash.addFlashAttribute("tituloPagina", usuarioLog.getUsuario());


            return "redirect:/inicio";

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            // Si las credenciales son incorrectas, manejamos el error
            session.setAttribute("msg", "Bad Credentials");
            log.info("Bad Credentials");
            flash.addFlashAttribute("error", "Correo y/o contraseña incorrectos");
            return "redirect:/?error=true";
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println("Error "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Método de autenticación
    private Authentication authenticate(String username, String password) throws Exception {
        try {
            // Usamos el AuthenticationManager para autenticar
            log.info("Authenticating user {}", username);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}