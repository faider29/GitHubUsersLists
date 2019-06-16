package ru.andrienko.githubuserslists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mNavController = Navigation.findNavController(this,R.id.nav_host_fragment);
        mNavController.navigate(R.id.fragmentUsers);
    }

}
