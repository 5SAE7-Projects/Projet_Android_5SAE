package tn.esprit.espritclubs.fragments.houssem.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.User;
import tn.esprit.espritclubs.fragments.houssem.main.LoginFragment;
import tn.esprit.espritclubs.fragments.houssem.user.adapter.UsersList;


public class HoussemFragment extends Fragment {
    public static ArrayList<User> champs = new ArrayList<>();
    private ListView listView;
    private SearchView searchView;

    private String selectedfilter = "all";
    private String currentsearchtext = "";
    private Button allfilter;
    private ImageView topfilter;
    private ImageView junglefilter;

    private AppDatabase database;

    private TextView aaa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_houssem, container, false);

        allfilter = view.findViewById(R.id.allfilter);
        topfilter = view.findViewById(R.id.topfilter);
        junglefilter = view.findViewById(R.id.junglefilter);

        setupData();
        setupListView(view);
        initSearch(view);

        // Set up the onClickListeners without XML attributes
        allfilter.setOnClickListener(v -> filterbyall());
        topfilter.setOnClickListener(v -> filterbytop());
        junglefilter.setOnClickListener(v -> filterbyjungle());

        return view;
    }

    private void initSearch(View view) {
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentsearchtext = s; // Update current search text
                filterList(selectedfilter); // Filter with the current selected filter
                return false;
            }
        });
    }

    private void setupListView(View view) {
        listView = view.findViewById(R.id.listView);
        UsersList adapter = new UsersList(getContext(), 0, champs);
        listView.setAdapter(adapter);
    }


    private void setupData() {
        if (champs.isEmpty()) {
            champs.clear(); // Clear before adding to avoid duplicates
            champs.addAll(database.userDao().getAll());
        }
    }

    public void filterList(String status) {
        selectedfilter = status;
        ArrayList<User> filteredList = new ArrayList<>();

        for (User champ : champs) {
            if (status.equals("all") || Objects.equals(champ.getGender(), status)) {
                if (currentsearchtext.isEmpty() ||
                        champ.getFirstName().toLowerCase().contains(currentsearchtext.toLowerCase()) ||
                        champ.getLastName().toLowerCase().contains(currentsearchtext.toLowerCase())) {
                    filteredList.add(champ);
                }
            }
        }

        UsersList adapter = new UsersList(getContext(), 0, filteredList);
        listView.setAdapter(adapter);
    }

    public void filterbyall() {
        currentsearchtext = ""; // Clear search text
        searchView.setQuery("", false);
        searchView.clearFocus();
        filterList("all");
    }

    public void filterbytop() {
        currentsearchtext = ""; // Clear search text
        searchView.setQuery("", false);
        searchView.clearFocus();
        filterList("Male"); // Adjust this value if your User class uses different gender identifiers
    }

    public void filterbyjungle() {
        currentsearchtext = ""; // Clear search text
        searchView.setQuery("", false);
        searchView.clearFocus();
        filterList("Female"); // Adjust this value if your User class uses different gender identifiers
    }


}
