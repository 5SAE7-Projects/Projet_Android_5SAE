package tn.esprit.espritclubs.fragments.manef;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.activities.ReservationActivity;


public class ManefFragment extends Fragment {


  private Button reserve_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_club, container, false);

        reserve_button= view.findViewById(R.id.reserve_button);

        reserve_button.setOnClickListener(manef->{

            Intent intent = new Intent(getContext(), ReservationActivity.class);
            //intent.putExtra("CLUB_NAME", club.getName());
            startActivity(intent);

        });





        return view;
    }
}