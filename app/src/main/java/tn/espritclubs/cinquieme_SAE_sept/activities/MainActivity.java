package tn.espritclubs.cinquieme_SAE_sept.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.fragments.mainapp.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout main_fragment_container;
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        main_fragment_container = findViewById(R.id.main_fragment_container);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment_container, new WelcomeFragment()).addToBackStack(null).commit();

    }

}