package com.epam.webchat.leonidivanov.services.impl;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.datalayer.repository.CustomizedUsersJpaRepository;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private CustomizedUsersJpaRepository usersJpaRepository;

    /**
     * Adds user to data source.
     *
     * @param user -- added user
     * @return added user
     */
    @Override
    public User addUser(User user) {
        return usersJpaRepository.saveAndFlush(user);
    }

    /**
     * Returns info about user
     *
     * @param id -- user ID
     * @return user info
     */
    @Override
    public Optional<User> getUserInfo(long id) {
        return usersJpaRepository.findById(id);
    }

    /**
     * Changes user info
     *
     * @param user -- changing data
     * @return changed user data
     */
    @Override
    public User changeUser(User user) {
        //TODO проверить редактирование, если не работает переделат на get
        // change and save
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
}
