package com.epam.webchat.leonidivanov.services.impl;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import com.epam.webchat.leonidivanov.datalayer.repository.CustomizedUsersJpaRepository;
import com.epam.webchat.leonidivanov.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
     * @param addingUser -- adding user
     * @return added user
     */
    @Override
    public User register(User addingUser) {
        addingUser.setUserRole(UserRole.USER);
        addingUser.setUserStatus(UserStatus.OFFLINE);
        addingUser.setPassword(getEncodedPassword(addingUser.getPassword()));
        log.debug("New user was added {}", addingUser);
        return usersJpaRepository.saveAndFlush(addingUser);
    }

    /**
     * Returns encoded password
     *
     * @param password -- user password
     * @return encoded password
     */
    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Returns info about user
     *
     * @param userID @return user info
     * @return user
     */
    @Override
    public User getUserData(Long userID) {
        log.debug("Getting user info with ID: {}", userID);
        User user = usersJpaRepository.findById(userID).get();
        log.debug("User info is {}", user);
        return user;
    }

    /**
     * Bans user from chat
     *
     * @param id -- baned user ID
     * @return user info
     */
    @Override
    public User banUser(long id) {
        log.debug("Banning user if id {}", id);
        User user = usersJpaRepository.findById(id).get();
        user.setUserStatus(UserStatus.BANNED);
        log.debug("User {} was banned", user);
        usersJpaRepository.saveAndFlush(user);
        return user;
    }

    /**
     * Returns list of all users
     *
     * @return list of all users
     */
    @Override
    public List<User> getAllUsers() {
        log.debug("Getting info about all users");
        return usersJpaRepository.findAll();
    }

    /**
     * Change user status from OFFLINE to ONLINE
     *
     * @param user -- user login
     */
    @Override
    public void login(User user) {
        log.debug("Logining user {}", user);
        user.setUserStatus(UserStatus.ONLINE);
        usersJpaRepository.saveAndFlush(user);
    }

    /**
     * Change user status from ONLINE to OFFLINE
     *
     * @param user -- user ID
     */
    @Override
    public void logout(User user) {
        log.debug("Logout user {}", user);
        user.setUserStatus(UserStatus.OFFLINE);
        usersJpaRepository.saveAndFlush(user);
    }

    /**
     * Return find user data by user login
     *
     * @param login -- user login
     * @return user data
     */
    @Override
    public User findByUserLogin(String login) {
        log.debug("Finding user with login {}", login);
        User userByLogin = usersJpaRepository.findUserByLogin(login);
        log.debug("Founded user {}", userByLogin);
        return userByLogin;
    }

    @Override
    public User unbanUser(Long userId) {
        log.debug("Unbanning user with id {}", userId);
        User user = usersJpaRepository.findById(userId).get();
        user.setUserStatus(UserStatus.OFFLINE);
        log.debug("User was unbanned");
        usersJpaRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User findById(Long authorId) {
        return usersJpaRepository.findById(authorId).get();
    }
}
