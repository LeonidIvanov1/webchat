package com.epam.webchat.leonidivanov.services;

import com.epam.webchat.leonidivanov.datalayer.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Describes methods to work with User entity.
 */
public interface UserService {
    /**
     * Adds user to data source.
     *
     * @param user -- added user
     * @return added user
     */
    User addUser(User user);

    /**
     * Returns info about user
     *
     * @param id -- user ID
     * @return user info
     */
    Optional<User> getUserInfo(long id);

    /**
     * Changes user info
     *
     * @param user -- changing data
     * @return changed user data
     */
    User changeUser(User user);

    /**
     * Deletes user data from data source
     *
     * @param id -- deleted user ID
     */
    void deleteUser(long id);

    /**
     * Kicks user from chat
     *
     * @param id -- kicked user ID
     */
    void kickUser(long id);

    /**
     * Bans user from chat
     *
     * @param id -- baned user ID
     */
    void banUser(long id);

    /**
     * Returns list of online users
     *
     * @return list of online users
     */
    List<User> getOnlineUsers();

    /**
     * Returns list of all users
     *
     * @return list of all users
     */
    List<User> getAllUsers();

}
