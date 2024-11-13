package tn.esprit.espritclubs.fragments.houssem.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.dao.ReservationDao;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Reservation;

public class HoussemFragment extends Fragment {
    private EditText nameEditText, emailEditText, dateEditText;
    private Button submitButton;
    private ReservationDao reservationDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_houssem, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        dateEditText = view.findViewById(R.id.dateEditText);
        submitButton = view.findViewById(R.id.submitButton);

        // Initialize ReservationDao
        reservationDao = AppDatabase.getDatabase(getContext()).reservationDao();

        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || date.isEmpty()) {
                Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Create a reservation object with proper data
                Reservation reservation = new Reservation(email, name, System.currentTimeMillis(), "clubesprit");

                // Insert reservation into the database
                new Thread(() -> {
                    reservationDao.insertReservation(reservation);
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Reservation Confirmed for " + "clubesprit", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            }
        });

        // Load all reservations (for testing purposes)
        loadAllReservations();

        return view;
    }

    private void loadAllReservations() {
        // Fetch all reservations from the database
        LiveData<List<Reservation>> allReservations = reservationDao.getAllReservations();
        allReservations.observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                // For testing purposes, show the count of reservations in a Toast
                Toast.makeText(getContext(), "Total reservations: " + reservations.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
