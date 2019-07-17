package com.kamkry.app.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class UserRole implements GrantedAuthority {

    private Integer id;
    private String role;

    @JsonIgnore
    private Set<AppUser> users = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> user) {
        this.users = user;
    }

    @Column(name = "role_name")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient
    @Override
    public String getAuthority() {
        return role;
    }
}
