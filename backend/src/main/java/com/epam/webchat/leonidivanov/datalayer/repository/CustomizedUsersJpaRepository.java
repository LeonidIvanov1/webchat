package com.epam.webchat.leonidivanov.datalayer.repository;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizedUsersJpaRepository extends JpaRepository<User, Long> {
    /**
     * Returns online users from data source
     * @return
     */
    @Query(value = "select u from Users u where u.status = 'ONLINE'",
            nativeQuery = true)
    List<User> getOnlineUsers();
    User findUserByLogin(String login);

}
