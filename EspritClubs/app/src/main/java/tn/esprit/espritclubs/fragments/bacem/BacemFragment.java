package tn.esprit.espritclubs.fragments.bacem;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import tn.esprit.espritclubs.R;

public class BacemFragment extends Fragment {

    private FrameLayout frameLayout;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bacem, container, false);

        // Initialize views
        frameLayout = view.findViewById(R.id.frameLayout);
        tabLayout = view.findViewById(R.id.tabLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        addButton = view.findViewById(R.id.addButton);

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
                        fragment = new FavoritesFragment();
                        break;
                    case 2:
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
}
