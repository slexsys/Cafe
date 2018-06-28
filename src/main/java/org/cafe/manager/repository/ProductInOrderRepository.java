package org.cafe.manager.repository;

import org.cafe.manager.entity.Order;
import org.cafe.manager.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
    @Query("SELECT po FROM ProductInOrder po WHERE po.order = ?1 AND po.status = org.cafe.manager.entity.comp.ProductInOrderStatus.Active")
    List<ProductInOrder> findProductInOrderByOrder(Order order);

    @Query("SELECT coalesce(SUM(po.amount), 0) FROM ProductInOrder po WHERE po.order = ?1 AND po.status = org.cafe.manager.entity.comp.ProductInOrderStatus.Active")
    Double getTotal(Order order);
}
