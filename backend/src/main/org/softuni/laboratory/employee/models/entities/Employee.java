package org.softuni.laboratory.employee.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.laboratory.analysis.models.Analysis;
import org.softuni.laboratory.occurrence.models.Occurrence;
import org.softuni.laboratory.core.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "employee")
    private List<Occurrence> occurrences;

    @OneToMany(mappedBy = "employee")
    private List<Analysis> analyzes;

    public Employee() {
        this.roles = new ArrayList<Role>();
        this.analyzes = new ArrayList<Analysis>();
        this.occurrences = new ArrayList<Occurrence>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) { this.username = username; }

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


    public List<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public List<Analysis> getAnalyzes() {
        return analyzes;
    }

    public void setAnalyzes(List<Analysis> analyzes) {
        this.analyzes = analyzes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
