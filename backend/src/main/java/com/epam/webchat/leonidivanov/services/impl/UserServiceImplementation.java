package com.epam.webchat.leonidivanov.services.impl;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import com.epam.webchat.leonidivanov.datalayer.repository.CustomizedUsersJpaRepository;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final CustomizedUsersJpaRepository usersJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(
            CustomizedUsersJpaRepository usersJpaRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.usersJpaRepository = usersJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Adds user to data source.
     *
     * @param user -- added user
     * @return added user
     */
    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(UserStatus.OFFLINE);
        return usersJpaRepository.saveAndFlush(user);
    }

    /**
     * Returns info about user
     *
     * @param id -- user ID
     * @return user info
     */
    @Override
    public User getUserData(long id) {
        return usersJpaRepository.findById(id).get();
    }

    /**
     * Changes user info
     *
     * @param user -- changing data
     * @return changed user data
     */
    @Override
    public User changeUser(User user) {
        return usersJpaRepository.saveAndFlush(user);
    }

    /**
     * Deletes user data from data source
     *
     * @param id -- deleted user ID
     */
    @Override
    public void deleteUser(long id) {
        usersJpaRepository.deleteById(id);
    }

    /**
     * Kicks user from chat
     *
     * @param id -- kicked user ID
     */
    @Override
    public void kickUser(long id) {
        usersJpaRepository.kickUser(id);
    }

    /**
     * Bans user from chat
     *
     * @param id -- baned user ID
     */
    @Override
    public void banUser(long id) {
        usersJpaRepository.banUser(id);
    }

    /**
     * Returns list of online users
     *
     * @return list of online users
     */
    @Override
    public List<User> getOnlineUsers() {
        return usersJpaRepository.getOnlineUsers();
    }

    /**
     * Returns list of all users
     *
     * @return list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return usersJpaRepository.findAll();
    }

    /**
     * Change user status from OFFLINE to ONLINE
     *
     * @param login    -- user login
     * @param password -- user password
     */
    @Override
    public void login(String login, String password) {

    }

    /**
     * Change user status from ONLINE to OFFLINE
     *
     * @param userId -- user ID
     */
    @Override
    public void logout(Long userId) {

    }

    /**
     * Return find user data by user login
     *
     * @param login -- user login
     * @return user data
     */
    @Override
    public User findByLogin(String login) {
        return usersJpaRepository.findUserByLogin(login);
    }
}
