package com.nalas.pnccontrollers.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sec02_roles")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private String id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
