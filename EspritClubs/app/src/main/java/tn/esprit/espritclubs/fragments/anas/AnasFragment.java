package tn.esprit.espritclubs.fragments.anas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Event;
import tn.esprit.espritclubs.fragments.houssem.main.LoginFragment;


public class AnasFragment extends Fragment {

   private Button addNew;
   private TextView test;
   private ListView eventListView;
   private AppDatabase appDatabase;
   //public static ArrayList<Event> events = new ArrayList<>();






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anas, container, false);
        getElements(view);
        addNew.setOnClickListener(e->{
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.main_content, new CreateEvent()).commit();
        });

        eventListView =(ListView) view.findViewById(R.id.listEvent);
        appDatabase = AppDatabase.getDatabase(getContext());
        List<Event> events = appDatabase.eventDao().getAll();
        EventList adapter = new EventList(getContext(),0,events);
        eventListView.setAdapter(adapter);


        return view;
    }

    private void getElements(View view) {
        addNew = view.findViewById(R.id.addNew);
    }
}