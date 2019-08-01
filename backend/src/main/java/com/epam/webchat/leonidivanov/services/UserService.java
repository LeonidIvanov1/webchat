package com.epam.webchat.leonidivanov.services;

import com.epam.webchat.leonidivanov.datalayer.entity.User;

import java.util.List;

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
    User register(User user);

    /**
     * Returns info about user
     *
     * @param userID -- user ID
     * @return user data
     */
    User getUserData(Long userID);

    /**
     * Changes user info
     *
     * @param changingUser -- changing data
     * @return changed user data
     */
    User changeUser(User changingUser);

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

    /**
     * Change user status from OFFLINE to ONLINE
     *
     * @param user -- user login
     */
    void login(User user);

    /**
     * Change user status from ONLINE to OFFLINE
     *
     * @param user -- user ID
     */
    void logout(User user);

    /**
     * Return find user data by user login
     *
     * @param login -- user login
     * @return user data
     */
    User findByUserLogin(String login);

}
