package ru.andrienko.githubuserslists.interfaces;

import java.util.List;

import ru.andrienko.githubuserslists.entity.Profile;

/**
 * Created by Maxim Andrienko
 * 6/19/19
 */
public interface ProfileModelListener {


    void profileListLoad(Profile entityProfile );

    void error(String errorMessage);
}
