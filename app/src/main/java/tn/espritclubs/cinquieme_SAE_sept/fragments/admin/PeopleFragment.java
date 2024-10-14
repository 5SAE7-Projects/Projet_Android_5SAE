package tn.espritclubs.cinquieme_SAE_sept.fragments.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.adapters.UserAdapter;
import tn.espritclubs.cinquieme_SAE_sept.models.User;


public class PeopleFragment extends Fragment {

    private ListView userslistView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        userslistView = view.findViewById(R.id.userslistView);
        setUserAdapter();
        return view;
    }

    private void setUserAdapter() {
        UserAdapter userAdapter = new UserAdapter(getContext(), User.userArrayList);
        userslistView.setAdapter(userAdapter);
    }
}