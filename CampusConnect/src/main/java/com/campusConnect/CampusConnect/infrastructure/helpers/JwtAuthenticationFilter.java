package com.campusConnect.CampusConnect.infrastructure.helpers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{ //se estiende de once... para que JWT se convierta en filtro
    
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) throws IOException, ServletException{ 
        //recibe request, response y filtro interno que tiene excepciones en caso de error

        /*1 obtener token */
        final String token = getTokenFromRequest(request);
        /*2 Si el token es nulo  seguir con los otros filtros*/
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        /*3. obtener el usuario del token */
        String userName = this.jwtService.getUserNameFromToken(token);

        //si el username esta dentro del token y el security Context muestra que si nadie esta autenticado
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName); //busca y obtener en el repositorio y va buscar el usuario con ese correo
        
            //si el token es valido
            if (this.jwtService.isTokenValid(token, userDetails)) {

                //creamos la autenticacion y la registramos en el contexto de seguridad de spring
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName,null, userDetails.getAuthorities()); //con autenticacion nula

                /*SetDetails asignar detalles adicionales de la autenticacion basados en la fuente de la solicitud como direccion ip,
                sesion donde se hace la solicitud */
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //registra el token de autenticacion en el contexto de spring security efectivamente autenticando al usuario para la duracion de la solicitud
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); //agregamos todos los filtros

         
    }

    /* Metodo para obtener el token del request */
    public String getTokenFromRequest(HttpServletRequest request){

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //obtener JWT por cabecera con Sort (Ascendente, Des...) con el nombre AUTHORIZATION

        //el token no lo envian puro, tiene palabra Bearer token
        //si el string no viene vacio y ademas la cabecera empieza con la palabra reservada Bearer_ entonces
        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")) { 
            
            //eliminar los primeros 7 caracteres
            return authHeader.substring(7);
        }
        return null;

    }
}
