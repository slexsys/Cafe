package org.cafe.manager.repository;

import org.cafe.manager.entity.Order;
import org.cafe.manager.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.table = ?1 AND o.acct = 0")
    Order getByTable(Table table);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = org.cafe.manager.entity.comp.OrderStatus.Cancelled, o.acct = 1 WHERE o = ?1")
    void cancelOrder(Order order);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = org.cafe.manager.entity.comp.OrderStatus.Closed, o.acct = 1 WHERE o = ?1")
    void closeOrder(Order order);

    @Query("SELECT o.table FROM Order o WHERE o.status = org.cafe.manager.entity.comp.OrderStatus.Open")
    List<Table> findAllOpenedTables();
}
