package mx.sgahc.security;

import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.security.jwt.JWTAuthenticationFilter;
import mx.sgahc.security.jwt.JWTTokenProvider;
import mx.sgahc.security.logout.CustomLogoutSuccessHandler;
import mx.sgahc.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTTokenProvider tokenProvider;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService,
                                 JWTTokenProvider tokenProvider,
                                 CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.tokenProvider = tokenProvider;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/bootstrap/**", "/iconos/**", "/imagen/**", "/temas/**","inicio/inicio-sesion" , "/index",
                                "/registro/registro", "/registro/save-user","/registro/paciente-save", "/**").permitAll()
                        .requestMatchers("/inicio").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/registro/registro-datos").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/registro/registro-direccion").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/citas/cita-main").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/citas/citas-historial").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/citas/citas-historial/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/citas/cita-nueva").hasAnyAuthority("Paciente")
                        .requestMatchers("/citas/cita-nueva/{id}").hasAnyAuthority("Paciente")
                        .requestMatchers("/registro/registro-medico").hasAnyAuthority("Medico")
                        .requestMatchers("/registro/registro-medico/{id}").hasAnyAuthority("Medico")
                        .requestMatchers("/registro/registro-paciente").hasAnyAuthority("Paciente")
                        .requestMatchers("/diagnosticos").hasAnyAuthority("Medico")
                        .requestMatchers("/diagnosticos/diagnosticos-pacientes").hasAnyAuthority("Medico")
                        .requestMatchers("/diagnosticos/diagnosticos-de-paciente/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/diagnosticos/crear-diagnostico").hasAnyAuthority("Medico")
                        .requestMatchers("/diagnosticos/diagnostico-save").hasAnyAuthority("Medico")
                        .requestMatchers("/diagnosticos/eliminar-diagnostico/{id}").hasAnyAuthority("Medico")
                        .requestMatchers("/diagnosticos/diagnostico-paciente/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/recetas").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/recetas/recetar").hasAnyAuthority("Medico")
                        .requestMatchers("/recetas/ver-tratamiento").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/recetas/ver-tratamiento/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/recetas/tratamientos/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/antecedentes/").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/antecedentes/antecedentes-patologicos/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/antecedentes/crear-antecedente-patologico").hasAnyAuthority( "Paciente")
                        .requestMatchers("/antecedentes/antecedente-patologico-save").hasAnyAuthority( "Paciente")
                        .requestMatchers("/antecedentes/antecedentes-familiares/{id}").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/antecedentes/crear-antecedente-familiar").hasAnyAuthority( "Paciente")
                        .requestMatchers("/antecedentes/antecedente-familiar-save").hasAnyAuthority( "Paciente")
                        .requestMatchers("/historias").hasAnyAuthority("Medico", "Paciente")
                        .requestMatchers("/historias/detalles-paciente/{id}").hasAnyAuthority("Medico", "Paciente")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/") // Página personalizada de inicio de sesión
                        //.loginProcessingUrl("/inicio/inicio-sesion") // Endpoint donde Spring Security manejará el login
                        .successForwardUrl("/inicio/inicio-sesion")
                        .defaultSuccessUrl("/inicio", true)
                        .failureUrl("/?error=true") // Redirigir en caso de error
                        .usernameParameter("username") // El nombre del campo del formulario HTML
                        .passwordParameter("password") // El nombre del campo del formulario HTML
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/doLogout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID", "token") //NEW Cookies to clear
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                        .clearAuthentication(true)
                        .invalidateHttpSession(true))
                .addFilterAfter(new JWTAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder(11, new SecureRandom());
        //return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        //your AuthenticationProvider must return UserDetails object
        return new ProviderManager(authenticationProvider());
    }
}