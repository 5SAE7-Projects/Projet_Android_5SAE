package tn.esprit.espritclubs.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.fragments.bacem.FavoritesFragment;
import tn.esprit.espritclubs.fragments.bacem.MessagesFragment;
import tn.esprit.espritclubs.fragments.bacem.TaskFragment;
import tn.esprit.espritclubs.fragments.houssem.main.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout main_fragment_container;
    private Button getStartedButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        main_fragment_container = findViewById(R.id.main_fragment_container);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment_container, new WelcomeFragment()).addToBackStack(null).commit();
    }
}