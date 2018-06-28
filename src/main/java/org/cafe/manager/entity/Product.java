package org.cafe.manager.entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double cost;

    @OneToMany(mappedBy = "product")
    private Set<ProductInOrder> productInOrders;
    //endregion

    //region constructors
    public Product() {
    }

    public Product(String name, Double cost) {
        this.name = name;
        this.cost = cost;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Set<ProductInOrder> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(Set<ProductInOrder> productInOrders) {
        this.productInOrders = productInOrders;
    }
    //endregion
}
