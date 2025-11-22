package com.shihon.mydrowerapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Optional, makes status bar transparent
        setContentView(R.layout.activity_main);

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        // Set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My App");

        // Drawer toggle (hamburger icon)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle drawer menu clicks
        navigationView.setNavigationItemSelectedListener(item -> {
            handleMenuClick(item);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Load default fragment on app start
        if (savedInstanceState == null) {
            loadFragment(new FormFragment());
            navigationView.setCheckedItem(R.id.homeId);
        }

        // Handle back gestures and button
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    /**
     * Handle navigation drawer menu clicks
     */
    private void handleMenuClick(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        int id = item.getItemId();

        if (id == R.id.homeId) {
            selectedFragment = new HomeFragment(); // Default fragment
        } else if (id == R.id.listId ) {
            selectedFragment = new ListFragment();
//        } else if (id == R.id.messagesId) {
//            selectedFragment = new MessagesFragment();
//        } else if (id == R.id.nav_settings) {
//            selectedFragment = new SettingsFragment();
//        } else if (id == R.id.nav_logout) {
            finish(); // Close app on logout
            return;
        }

        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }
    }

    /**
     * Load fragment into the fragment container
     */
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}