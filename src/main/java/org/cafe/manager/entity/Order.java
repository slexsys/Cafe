package org.cafe.manager.entity;

import org.cafe.manager.entity.comp.OrderStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "orders")
public class Order {
    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long acct; //finished order number
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "tableId")
    private Table table;

    @OneToMany(mappedBy = "order")
    private Set<ProductInOrder> productInOrders;
    //endregion

    //region constructors
    public Order() {
    }

    public Order(long acct, OrderStatus status, Table table) {
        this.acct = acct;
        this.status = status;
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

    public Long getAcct() {
        return acct;
    }

    public void setAcct(Long acct) {
        this.acct = acct;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Set<ProductInOrder> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(Set<ProductInOrder> productInOrders) {
        this.productInOrders = productInOrders;
    }
    //endregion
}
