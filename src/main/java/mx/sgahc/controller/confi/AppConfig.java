package mx.sgahc.controller.confi;

import mx.sgahc.model.usuarios.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class AppConfig {

    @Bean
    public Usuario usuario(){
        return new Usuario();
    }
}
