package tn.esprit.espritclubs.fragments.manef;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.adapters.ClubAdapter;
import tn.esprit.espritclubs.entities.Club;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ClubListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ClubAdapter adapter;
    private ArrayList<Club> clubList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_list , container, false);

        // Initialize RecyclerView and set up the adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize club list with sample data
        clubList = new ArrayList<>();
        clubList.add(new Club("Aisec", R.drawable.aisec));
        clubList.add(new Club("Tunivison", R.drawable.tuni));

        // Set adapter with club data
        adapter = new ClubAdapter(clubList, club -> navigateToReservationForm(club));

        recyclerView.setAdapter(adapter);

        return view;
    }


    private void navigateToReservationForm(Club club) {
        // Navigate to ReservationFormFragment, passing club information
        tn.esprit.espritclubs.fragments.ReservationFormFragment reservationFragment = tn.esprit.espritclubs.fragments.ReservationFormFragment.newInstance(club.getName());
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, reservationFragment)
                .addToBackStack(null)
                .commit();
    }
}
