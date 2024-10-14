package tn.espritclubs.cinquieme_SAE_sept.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.HomeFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.PeopleFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.ProfileFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.admin.SearchFragment;
import tn.espritclubs.cinquieme_SAE_sept.fragments.mainapp.ResetPassword2Fragment;

public class AdminActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userid", getIntent().getIntExtra("userid", -1));
                profileFragment.setArguments(bundle);

                if (item.getItemId() == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.navigation_search) {
                    selectedFragment = new SearchFragment();
                } else if (item.getItemId() == R.id.navigation_people) {
                    selectedFragment = new PeopleFragment();
                } else if (item.getItemId() == R.id.navigation_profile) {
                    selectedFragment = profileFragment;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_content, selectedFragment)
                            .commit();
                }
                return true;
            }
        });

        // Set default fragment
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
}