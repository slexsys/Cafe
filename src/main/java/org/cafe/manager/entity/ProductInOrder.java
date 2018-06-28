package org.cafe.manager.entity;

import org.cafe.manager.entity.comp.ProductInOrderStatus;

import javax.persistence.*;

@Entity
public class ProductInOrder {
    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double qtty;
    private Double amount;
    private ProductInOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    //endregion

    //region constructors
    public ProductInOrder() {
    }

    public ProductInOrder(Double qtty, Double amount, Order order, Product product, ProductInOrderStatus status) {
        this.qtty = qtty;
        this.amount = amount;
        this.order = order;
        this.product = product;
        this.status = status;
    }
    //endregion

    //region getters setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQtty() {
        return qtty;
    }

    public void setQtty(Double qtty) {
        this.qtty = qtty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ProductInOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ProductInOrderStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    //endregion
}
