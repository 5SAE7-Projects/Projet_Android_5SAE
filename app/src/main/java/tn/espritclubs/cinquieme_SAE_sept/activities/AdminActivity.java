package tn.espritclubs.cinquieme_SAE_sept.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.HomeFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.PeopleFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.ProfileFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.SearchFragment;

public class AdminActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.nav_view);
        Button btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));

        // Set default fragment
        currentFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, currentFragment)
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Create the ProfileFragment with arguments
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userid", getIntent().getIntExtra("userid", -1));
                profileFragment.setArguments(bundle);

                // If statements to handle navigation item selection
                if (item.getItemId() == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.navigation_search) {
                    selectedFragment = new SearchFragment();
                } else if (item.getItemId() == R.id.navigation_people) {
                    selectedFragment = new PeopleFragment();
                } else if (item.getItemId() == R.id.navigation_profile) {
                    selectedFragment = profileFragment;
                } else if (item.getItemId() == R.id.navigation_settings) {
                    // Handle settings fragment if you have one
                }

                // Replace fragment only if it's different from the current one
                if (selectedFragment != null && !selectedFragment.getClass().equals(currentFragment.getClass())) {
                    currentFragment = selectedFragment;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_content, selectedFragment)
                            .commit();
                    drawerLayout.closeDrawers(); // Close the drawer after selection
                }
                return true;
            }
        });
    }
}