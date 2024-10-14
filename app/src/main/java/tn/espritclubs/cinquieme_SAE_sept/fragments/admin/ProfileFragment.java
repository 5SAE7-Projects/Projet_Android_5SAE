package tn.espritclubs.cinquieme_SAE_sept.fragments.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.models.User;
import tn.espritclubs.cinquieme_SAE_sept.services.SQLiteUserManager;


public class ProfileFragment extends Fragment {

    private TextView emailTextView, firstNameTextView, lastNameTextView, genderTextView, roleTextView, birthdayTextView, phoneNumberTextView, aboutMeTextView;
    private String email, firstName, lastName, gender, role, birthday, phoneNumber, aboutMe;
    private int userId;
    private User currentUser;
    private ImageView profileImageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initializeViews(view);
        getuserid();
        getconnecteduser();
        fillthetext();



        return view;
    }

    private void fillthetext() {
        emailTextView.setText(currentUser.getEmail());
        firstNameTextView.setText(currentUser.getFirstname());
        lastNameTextView.setText(currentUser.getLastname());
        genderTextView.setText(currentUser.getGender());
        roleTextView.setText(currentUser.getRole());
        birthdayTextView.setText(currentUser.getBirthday().toString());
        phoneNumberTextView.setText(currentUser.getPhoneNumber());
        aboutMeTextView.setText(currentUser.getAboutMe());

    }

    private void getconnecteduser() {
        SQLiteUserManager sqLiteManager = SQLiteUserManager.instanceOfDatabase(getContext());
        User.userArrayList.clear();
        sqLiteManager.populateUserListArray();
        currentUser = User.getUserForId(userId);
    }

    private void getuserid() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt("userid");
        }
    }

    private void initializeViews(View view) {
        emailTextView = view.findViewById(R.id.emailText);
        firstNameTextView = view.findViewById(R.id.firstNameText);
        lastNameTextView = view.findViewById(R.id.lastNameText);
        genderTextView = view.findViewById(R.id.genderText);
        roleTextView = view.findViewById(R.id.roleText);
        birthdayTextView = view.findViewById(R.id.birthdayText);
        phoneNumberTextView = view.findViewById(R.id.phoneText);
        aboutMeTextView = view.findViewById(R.id.aboutMeText);
        profileImageView = view.findViewById(R.id.profileImage);
    }
}