package org.cafe.manager.repository;

import org.cafe.manager.entity.Assign;
import org.cafe.manager.entity.Table;
import org.cafe.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignRepository extends JpaRepository<Assign, Long> {
    @Query("SELECT a.table FROM Assign a")
    List<Table> getAllTables();

    @Query("SELECT a.table FROM Assign a WHERE a.user = ?1")
    List<Table> findAllTablesByUser(User user);

    @Query("SELECT a.table FROM Assign a WHERE a.user = ?1 AND a.table NOT IN ?2")
    List<Table> findNotActiveTablesByUser(User user, List<Table> tables);

    @Query("SELECT a.table FROM Assign a INNER JOIN Order o ON o.table = a.table WHERE a.user = ?1 AND o.acct = 0 AND o.status = org.cafe.manager.entity.comp.OrderStatus.Open")
    List<Table> findActiveTablesByUser(User user);

    @Query("SELECT a.table FROM Assign a WHERE a.user = ?1")
    List<Table> findNotActiveTablesByUser(User user);
}
