package tn.esprit.espritclubs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.adapters.ClubAdapter;
import tn.esprit.espritclubs.entities.Club;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.dao.ClubDao;

public class ClubListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClubAdapter clubAdapter;
    private ClubDao clubDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_club_list);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase database = AppDatabase.getDatabase(this);
        // Initialize ClubDao
        clubDao = AppDatabase.getDatabase(this).clubDao();

        clubDao = database.clubDao();

        // Observe the LiveData of clubs
        clubDao.getAllClubsLive().observe(this, new Observer<List<Club>>() {
            @Override
            public void onChanged(List<Club> clubs) {
                if (clubs != null) {
                    // Update the RecyclerView with the latest data from the database
                    clubAdapter = new ClubAdapter(clubs, new ClubAdapter.OnItemClickListener() {
                        @Override
                        public void onReserveClicked(Club club) {



                            // Navigate to reservation screen when a "Reserve" button is clicked
                            Intent intent = new Intent(ClubListActivity.this, ReservationActivity.class);
                            intent.putExtra("CLUB_NAME", club.getName());
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(clubAdapter);
                } else {
                    // Handle case where the list is empty or null
                    Toast.makeText(ClubListActivity.this, "No clubs found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sample method to insert a new club (for testing purposes)
        insertSampleClub();
    }

    // Sample method to insert a new club into the database
    private void insertSampleClub() {
        new Thread(() -> {
            Club newClub = new Club("Aisec", R.drawable.aisec);
            Club newClub1 = new Club("Tunivision", R.drawable.tuni);// "Aisec" and its image
            Log.d("ClubListActivity", "Inserting club: " + newClub.getName());
            clubDao.insertClub(newClub);
        }).start();
    }

}
