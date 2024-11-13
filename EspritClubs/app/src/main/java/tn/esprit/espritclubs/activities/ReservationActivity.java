package tn.esprit.espritclubs.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.dao.ReservationDao;
import tn.esprit.espritclubs.entities.Reservation;
import tn.esprit.espritclubs.database.AppDatabase;

public class ReservationActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, dateEditText;
    private Button submitButton;
    private ReservationDao reservationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reservation_form);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        dateEditText = findViewById(R.id.dateEditText);
        submitButton = findViewById(R.id.submitButton);

        // Get club name from Intent
        String clubName = getIntent().getStringExtra("CLUB_NAME");

        // Initialize ReservationDao
        reservationDao = AppDatabase.getDatabase(this).reservationDao();

        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || date.isEmpty()) {
                Toast.makeText(ReservationActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Create a reservation object with proper data
                Reservation reservation = new Reservation(email, name, System.currentTimeMillis(), clubName);

                // Insert reservation into the database
                new Thread(() -> {
                    reservationDao.insertReservation(reservation);
                    runOnUiThread(() -> Toast.makeText(ReservationActivity.this, "Reservation Confirmed for " + clubName, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        // Example: Get all reservations and display them (for testing purposes)
        loadAllReservations();
    }

    private void loadAllReservations() {
        // Fetch all reservations from the database
        LiveData<List<Reservation>> allReservations = reservationDao.getAllReservations();
        allReservations.observe(this, new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                // For now, just show the count of reservations in a Toast
                Toast.makeText(ReservationActivity.this, "Total reservations: " + reservations.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
