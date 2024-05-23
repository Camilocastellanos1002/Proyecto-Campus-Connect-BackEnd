package com.campusConnect.CampusConnect.domain.entities.fuertes;

import java.math.BigInteger;
// import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.campusConnect.CampusConnect.domain.entities.debiles.Administrador;
import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;
import com.campusConnect.CampusConnect.domain.entities.debiles.Profesor;
import com.campusConnect.CampusConnect.util.enums.Rol;
import com.campusConnect.CampusConnect.util.enums.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUsuario;

    @Column(nullable = false)// Atributo obligatorio
    private String nombre;

    @Column(nullable = false)
    private String apellidos;
    
    @Enumerated(EnumType.STRING) // Acepta valores existentes, en MySQL es un check in con 3 tipos de valores determinados
    private TipoDocumento tipoDocumento;

    @Column(nullable = false, unique = true) // Atributo obligatorio y es unico
    private BigInteger documento;

    @Column(nullable = false)
    private String fechaNacimiento;
    
    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(nullable = false, length = 100)
    private String password;

    /* REVISAR BIEN SUGERENCIA DE KEVIN, subir imagen y una api que pase eso a url*/
    private String foto;

    /* Relación tabla Profesor */
    @OneToOne(mappedBy = "usuario")
    private Profesor profesor;

    /* Relación tabla Administrador */
    @OneToOne(mappedBy = "usuario")
    private Administrador administrador;

    /* Relación tabla Estudiante */
    @OneToOne(mappedBy = "usuario")
    private Estudiante estudiante;

    /* Métodos Spring Security */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

   //obtener usuario
   @Override
   public String getUsername() {
       return this.correo;
   }

   @Override
   public boolean isAccountNonExpired() {
       return true;
   }

   @Override
   public boolean isAccountNonLocked() {
       return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}