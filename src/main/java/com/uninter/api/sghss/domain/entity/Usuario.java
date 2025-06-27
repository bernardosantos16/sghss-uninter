package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    public Usuario(String login, String senha, UserRole userRole) {
        this.login = login;
        this.senha = senha;
        this.userRole = userRole;
    }
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.userRole = UserRole.USUARIO;
    }

    public void usuarioMedico(){
        this.userRole = UserRole.MEDICO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
        }
        if (this.userRole == UserRole.MEDICO) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
