package com.example.homework9;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    // Declare the home fragments for each navigation
    private Fragment homeFragment = new HomeFragment();
    private Fragment topicsFragment = new TopicsFragment();
    private Fragment createFragment = new CreateFragment();
    private Fragment guideFragment = new GuideFragment();
    private Fragment profileFragment = new ProfileFragment();

    private Bundle homeFragmentArgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the initial fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.bottom_home:
//                            Toast.makeText(MainActivity.this, "首页", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                            return true;
                        case R.id.bottom_topics:
//                            Toast.makeText(MainActivity.this, "话题", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, topicsFragment).commit();
                            return true;
                        case R.id.bottom_create:
                            Toast.makeText(MainActivity.this, "创造", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createFragment).commit();
                            return true;
                        case R.id.bottom_guide:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, guideFragment).commit();
                            return true;
                        case R.id.bottom_mine:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                            return true;
                        default:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                            Toast.makeText(MainActivity.this, "Invalid Selection", Toast.LENGTH_SHORT).show();
                            return true;
                    }
                }
        );
    }

    public void navigateToHomeFragment(Bundle args) {
        homeFragmentArgs = args;
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();


        Post tPost = args.getParcelable("POST_RESULT");
        Log.d("DEBUG", tPost.getTitle());



        // Update selected item in Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
    }

}