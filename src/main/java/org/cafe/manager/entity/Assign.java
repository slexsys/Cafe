package org.cafe.manager.entity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "assigns")
public class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tableId")
    private Table table;

    //region constructors
    public Assign() {
    }

    public Assign(User user, Table table) {
        this.user = user;
        this.table = table;
    }
    //endregion

    //region getters setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    //endregion
}
