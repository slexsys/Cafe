package org.cafe.manager.repository;

import org.cafe.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByUserByLogin(String username);

    @Query("SELECT u FROM User u WHERE u.type =  org.cafe.manager.entity.comp.UserType.Waiter")
    List<User> findAllWaiters();

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);
}
