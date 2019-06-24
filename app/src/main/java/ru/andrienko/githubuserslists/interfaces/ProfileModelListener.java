package ru.andrienko.githubuserslists.interfaces;

import ru.andrienko.githubuserslists.entity.Profile;

public interface ProfileModelListener {

    void profileListLoad(Profile entityProfile );

    void error(String errorMessage);
}
