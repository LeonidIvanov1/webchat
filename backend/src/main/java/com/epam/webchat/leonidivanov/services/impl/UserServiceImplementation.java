package com.epam.webchat.leonidivanov.services.impl;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
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
     * @param addingUser -- adding user
     * @return added user
     */
    @Override
    public User register(User addingUser) {
        addingUser.setUserRole(UserRole.USER);
        addingUser.setUserStatus(UserStatus.OFFLINE);
        addingUser.setPassword(getEncodedPassword(addingUser.getPassword()));
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
        return usersJpaRepository.findById(userID).get();
    }

    /**
     * Changes user info
     *
     * @param changingUser -- changing data
     * @return changed user data
     */
    @Override
    public User changeUser(User changingUser) {
        User user = usersJpaRepository.findUserByLogin(changingUser.getLogin());
        user.setEmail(changingUser.getEmail());
        user.setFullName(changingUser.getFullName());
        user.setPassword(getEncodedPassword(changingUser.getPassword()));
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
        User user = usersJpaRepository.findById(id).get();
        user.setUserStatus(UserStatus.KICKED);
        usersJpaRepository.saveAndFlush(user);
    }

    /**
     * Bans user from chat
     *
     * @param id -- baned user ID
     */
    @Override
    public void banUser(long id) {
        User user = usersJpaRepository.findById(id).get();
        user.setUserStatus(UserStatus.BANNED);
        usersJpaRepository.saveAndFlush(user);
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
     * @param user -- user login
     */
    @Override
    public void login(User user) {
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
        return usersJpaRepository.findUserByLogin(login);
    }
}
