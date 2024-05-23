package com.campusConnect.CampusConnect.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {
    
    //es nesesario informacion del usuario para el login por lo que se inyecta el repositorio de usuario
    @Autowired
    private final UsuarioRepository usuarioRepository;

    /* Autentication Manager
     * Utiliza la configuracion de spring security para obtener una configuracion por defecto,
     * es decir que usuario esta autenticado y decir que puede o no puede hacer
     */
    @Bean //anotacion para sobreescribir configuracion de spring
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config
    )throws Exception{ //el puede fallar por lo que en caso tal enviar una exception
        return config.getAuthenticationManager();
    }
    /* AuthenticationProvider se encarga de guardar info del usuario
     * 
     * Este metodo crea y configura un DaoAuthen.... que provee datos a la app para guardar credenciales, info y tipo de encryptado de la constraseña del usuario
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); //clase que guarda user y password

        authenticationProvider.setPasswordEncoder(this.passwordEncoder()); //recibe el tipo de cifrado de la contraseña
        authenticationProvider.setUserDetailsService(userDetailsService()); //llamamos los detalles de usuario

        return authenticationProvider;
    }

    /*
     * Servicio de UserDetails para realizar la busqueda del usuario para cargar detalles del usuario durante la autenticacion
    */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> usuarioRepository.findByCorreo(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado")); //buscar en el repositorio el usuario por correo y en caso T devuelve detalles del usuario de lo contrario envia exception de usuario no encontrado
    }


    /**
     * @Bean se define para PassqordEncoder
     * Este encoder es utilizado para encriptar las contraseñas en la aplicacion,
     * retorna una instancia de BCryptoPasswordEncoder, es un metodo de cifrado o hash 
     * fuertemente y altamente utilizado
     */
    @Bean //sobreescribir configuraciones de springboot
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
