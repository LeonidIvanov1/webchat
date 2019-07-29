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
    @Query(value = "select u from Users u where u.status = 'ONLINE'",
            nativeQuery = true)
    List<User> getOnlineUsers();

    @Modifying(clearAutomatically = true)
    @Query(value = "update USERS u set u.status = 'KICKED' where u.id = :userId",
            nativeQuery = true)
    void kickUser(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update USERS u set u.status = 'BANNED' where u.id = :userId",
            nativeQuery = true)
    void banUser(@Param("userId") Long userId);
}
