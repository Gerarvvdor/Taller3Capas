package com.nalas.pnccontrollers.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "sec01_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private String email;
    private Boolean active = true;

<<<<<<< HEAD
    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(
            name = "sec02_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    List<Role> roles;

=======

    @ManyToMany(FetchType.EAGER)
    @JoinTable{
        name = "sec02_permissions",
        joinColummns = @JoinColumns(name ="user_id"),
        inverseJoinColunms = @JoinColumns(name = "role_id")
    }
>>>>>>> 61448c1daff805b300bbd73b9e411ef03d8fbe77
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}

