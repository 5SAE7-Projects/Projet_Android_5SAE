package tn.esprit.espritclubs.fragments.bacem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tn.esprit.espritclubs.OnDialogCloseListener;
import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.activities.bacemActivities.Adapter.ToDoAdapter;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Task;
import tn.esprit.espritclubs.entities.User;
import tn.esprit.espritclubs.fragments.houssem.main.LoginFragment;

public class BacemFragment extends Fragment implements OnDialogCloseListener {

    private FrameLayout frameLayout;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private ImageView imageView;
    private TextView textView;
    private TextView textView2;
    private List<Task> mList;
    private ToDoAdapter adapter;
    private User currentUser;
    private AppDatabase database;
    private SharedPreferences sp;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BacemFragment() {
        // Required empty public constructor
    }

    public static BacemFragment newInstance(String param1, String param2) {
        BacemFragment fragment = new BacemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences(LoginFragment.SHARED_NAME, Context.MODE_PRIVATE);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mList = new ArrayList<>(); // Initialize the list here
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bacem, container, false);

        // Initialize views
        database= AppDatabase.getDatabase(getContext());
        currentUser = database.userDao().getUserByUid(Integer.parseInt(sp.getString("id","1")));
        frameLayout = view.findViewById(R.id.frameLayout);
        imageView = view.findViewById(R.id.imageView00);
        tabLayout = view.findViewById(R.id.tabLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        addButton = view.findViewById(R.id.addButton1);
        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        textView.setText(currentUser.getFirstName());
        textView.setText(currentUser.getEmail());
        imageView.set
        // Initialize adapter with parent fragment for handling child fragment manager
        //adapter = new ToDoAdapter(this);  // Pass `this` as BacemFragment instance
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(adapter);

        // Populate list, reverse it, and update the adapter
        //Collections.reverse(mList);
        //adapter.setTasks(mList);
        //adapter.notifyDataSetChanged();

        // Set up addButton listener to add new task using AddNewTask dialog
        //addButton.setOnClickListener(view1 -> AddNewTask.newInstance().show(getChildFragmentManager(), AddNewTask.TAG));

        // Set up initial fragment in frameLayout
        getChildFragmentManager().beginTransaction().replace(R.id.frameLayout, new MessagesFragment())
                .addToBackStack(null)
                .commit();

        // Set up tabLayout listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new MessagesFragment();
                        break;
                    case 1:
                        fragment = new TaskFragment();
                        break;

                }
                if (fragment != null) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return view;
    }

    @Override
    public void onDialogClose(DialogInterface dialog) {
        Collections.reverse(mList);
        //adapter.setTasks(mList);
        //adapter.notifyDataSetChanged();
    }
}
