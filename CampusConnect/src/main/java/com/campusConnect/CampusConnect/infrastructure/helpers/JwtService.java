package com.campusConnect.CampusConnect.infrastructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component //un component puede ser un repositorio, entidad, servicio, .....  para poder trabajar servicio con un component
public class JwtService {

    /* 1 Crear firma o clave */
        /* llave sin codificar base64: 
            pag para codificar: https://www.base64encode.org/
            mi super clave secreta secreta secreta, mi super clave secreta secreta secreta 
        */ 
        /* llave codificada en base 64 */
        private final String SECRET_KEY = "bWkgc3VwZXIgY2xhdmUgc2VjcmV0YSBzZWNyZXRhIHNlY3JldGEsIG1pIHN1cGVyIGNsYXZlIHNlY3JldGEgc2VjcmV0YSBzZWNyZXRh";

        /*2  metodo para encriptar la clave secreta */
        public SecretKey getKey(){
            //array de bytes, decodifica la llave base 64 y luego lo convierte a bytes
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); 

            //se retorna la llave cifrada
            return Keys.hmacShaKeyFor(keyBytes);
        }
        /*3 Construir el JWT */
        public String getToken(Map<String,Object> claims, Usuario user){

            return Jwts.builder()
                    .claims(claims) //agrego el cuerpo del jwt
                    .subject(user.getCorreo()) //agrego de quien es el jwt
                    .issuedAt(new Date(System.currentTimeMillis())) //fecha de creacion que viene del sistema
                    .expiration(new Date(System.currentTimeMillis()+ 1000*60*60*24)) //fecha de expiracion
                    .signWith(this.getKey()) //firmar el token
                    .compact();
        }
        /*  4 mEtodo para obtener el jwt*/
        public String getToken(Usuario user){
            //crear el map de claims
            Map<String, Object> claims = new HashMap<>();

            claims.put("id", user.getIdUsuario());
            claims.put("role", user.getRol().name());

            return getToken(claims, user);

        }


    /**Para obtener los claims desarmando el token  */
        public Claims getallClaims(String token){
            return Jwts
                    .parser() //desarmamos JWT
                    .verifyWith(this.getKey()) //verificamos con la firma del servidor
                    .build() //armar de nuevo
                    .parseSignedClaims(token)   //convertir de base 64 a json el payload
                    .getPayload();  //extraemos la informacion del payload (cuerpo del JWT)
        }

        /* Metodo para extraer cada uno de los claims, de forma especifica */
            //recibe un generico y devuelve el mismo generico (T)
        public <T> T getClaim(String token,Function<Claims, T> claimsResolver){     //recibe token y una funcion cual es el claim por defecto y de tipo generico (T)

            final Claims claims = this.getallClaims(token); //guarda todos los claims
            return claimsResolver.apply(claims);    //retornamos un claim en especifico
        }

        public String getUserNameFromToken(String token){
            return this.getClaim(token, Claims::getSubject); //obtiene el usuario del token
        }

        public Date getExpiration(String token){
            return this.getClaim(token, Claims::getExpiration); //obtiene el claim de fecha de experiacion del token
        }
    
    /*Metodos para validar el token */
        
        /* validar si el token esta expirado o no */
        public boolean isTokenExpired(String token){
            return this.getExpiration(token).before(new Date()); //before compara si una fecha esta despues de otra, verifica si paso la fecha de hoy
        }

        /* validar si el token es valido
         * validar la informacion del usuario
         * 
         * si el usuario viene en el token y el token no esta expirado retorna T, de lo contrario F
        */
        public boolean isTokenValid(String token, UserDetails userDetails){
            String userName = this.getUserNameFromToken(token);
            return (userName.equals(userDetails.getUsername()) && !this.isTokenExpired(token)); //condicional que retorna T o F
        }
    
}
