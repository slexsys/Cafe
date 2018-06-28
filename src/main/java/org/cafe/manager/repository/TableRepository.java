package org.cafe.manager.repository;

import org.cafe.manager.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {
    @Query("SELECT t FROM Table t WHERE t NOT IN ?1")
    List<Table> findAllNotContain(List<Table> tables);
}
