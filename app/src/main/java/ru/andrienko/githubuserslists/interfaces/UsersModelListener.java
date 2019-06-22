package ru.andrienko.githubuserslists.interfaces;

import java.util.List;

import ru.andrienko.githubuserslists.entity.User;

/**
 * Created by Maxim Andrienko
 * 6/19/19
 */
public interface UsersModelListener {

    void usersListLoad(List<User> entityUsers );

    void error(String errorMessage);
}
