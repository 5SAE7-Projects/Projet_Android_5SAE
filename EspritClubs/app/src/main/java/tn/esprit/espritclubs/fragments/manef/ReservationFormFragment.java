package tn.esprit.espritclubs.fragments.manef;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import tn.esprit.espritclubs.R;

public class ReservationFormFragment extends Fragment {

    private static final String ARG_CLUB_NAME = "club_name";
    private String clubName;
    private EditText editName, editEmail, editDate;

    public static ReservationFormFragment newInstance(String clubName) {
        ReservationFormFragment fragment = new ReservationFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLUB_NAME, clubName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation_form, container, false);

        // Retrieve club name
        if (getArguments() != null) {
            clubName = getArguments().getString(ARG_CLUB_NAME);
        }

        editName = view.findViewById(R.id.nameEditText);
        editEmail = view.findViewById(R.id.emailEditText);
        editDate = view.findViewById(R.id.dateEditText);
        Button submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            if (validateInputs()) {
                saveReservation();
            }
        });

        return view;
    }

    private boolean validateInputs() {
        return !editName.getText().toString().isEmpty() &&
                !editEmail.getText().toString().isEmpty() &&
                !editDate.getText().toString().isEmpty();
    }

    private void saveReservation() {
        // Simulate saving reservation data
        Toast.makeText(getContext(), "Reservation made for " + clubName, Toast.LENGTH_SHORT).show();
    }
}
