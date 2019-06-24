package ru.andrienko.githubuserslists.interfaces;

import java.util.List;

import ru.andrienko.githubuserslists.entity.User;

public interface UsersModelListener {

    void usersListLoad(List<User> entityUsers );

    void error(String errorMessage);

    void showLoad(Boolean isLoad);
}
