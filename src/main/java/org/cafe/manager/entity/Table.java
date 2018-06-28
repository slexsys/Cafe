package org.cafe.manager.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "table")
    private Set<Order> orders;

    @OneToMany(mappedBy = "table")
    private Set<Assign> assigns;
    //endregion

    //region constructors
    public Table() {
    }

    public Table(String name) {
        this.name = name;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Assign> getAssigns() {
        return assigns;
    }

    public void setAssigns(Set<Assign> assigns) {
        this.assigns = assigns;
    }
    //endregion
}
