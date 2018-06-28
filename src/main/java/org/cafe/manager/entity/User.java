package org.cafe.manager.entity;

import org.cafe.manager.entity.comp.UserType;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private UserType type;

    @OneToMany(mappedBy = "user")
    private Set<Assign> assigns;
    //endregion

    //region constructors
    public User() {
    }

    public User(String name, String password, UserType type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }
    //endregion

    //region getters setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Set<Assign> getAssigns() {
        return assigns;
    }

    public void setAssigns(Set<Assign> assigns) {
        this.assigns = assigns;
    }
    //endregion
}
