package tn.espritclubs.cinquieme_SAE_sept.fragments.mainapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.activities.AdminActivity;
import tn.espritclubs.cinquieme_SAE_sept.activities.UserActivity;
import tn.espritclubs.cinquieme_SAE_sept.models.User;
import tn.espritclubs.cinquieme_SAE_sept.services.SQLiteUserManager;


public class LogInFragment extends Fragment {

    private TextView signup, warningTextView, forgotPassword;
    private EditText username, password;
    private Button login;
    private User connectedUser;;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadFromDBToMemory();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+User.userArrayList);
    }

    private void loadFromDBToMemory() {
        SQLiteUserManager sqLiteManager = SQLiteUserManager.instanceOfDatabase(getContext());
        User.userArrayList.clear();
        sqLiteManager.populateUserListArray();
        sqLiteManager.initializeAdminUser();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_log_in, container, false);

        signup= view.findViewById(R.id.signup);
        forgotPassword= view.findViewById(R.id.forgotPassword);
        warningTextView= view.findViewById(R.id.warningTextView);
        username= view.findViewById(R.id.username);
        password= view.findViewById(R.id.password);
        login= view.findViewById(R.id.login);

        forgotPassword.setOnClickListener(houssem->{
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.main_fragment_container, new ResetPassword1Fragment()).commit();
        });

        signup.setOnClickListener(houssem->{
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.main_fragment_container, new SignUpFragment()).commit();
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verifiButtonLogin();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verifiButtonLogin();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        login.setOnClickListener(houssem->{
            detectRole();
        });
        return view;
    }

    private void verifiButtonLogin() {
        warningTextView.setVisibility(View.GONE);
        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            login.setEnabled(false);
        }
        else{
            login.setEnabled(true);
        }
    }

    private void detectRole() {
        User user = User.getUserForEmail(username.getText().toString());
        if (user == null) {
            warningTextView.setText("This email doesn't exist");
            warningTextView.setVisibility(View.VISIBLE);
            return;
        }
        if (!user.getPassword().equals(password.getText().toString())) {
            warningTextView.setText("The password is incorrect");
            warningTextView.setVisibility(View.VISIBLE);
            return;
        }
        connectedUser = user;
        System.out.println("and his name is "+connectedUser.toString());
        switchActivityBasedOnRole(connectedUser);
        warningTextView.setVisibility(View.GONE);
    }

    private void switchActivityBasedOnRole(User user) {
        Intent intentadmin= new Intent(getActivity(), AdminActivity.class);
        Intent intentuser= new Intent(getActivity(), UserActivity.class);
        if (user == null){
            warningTextView.setText("email or password incorrect");
            warningTextView.setVisibility(View.VISIBLE);
        }
        else {
            if (Objects.equals(user.getRole(), "admin")){
                intentadmin.putExtra("userid", connectedUser.getId());
                startActivity(intentadmin);
            }
            else{
                if (Objects.equals(user.getRole(), "user")||Objects.equals(user.getRole(), "student")||Objects.equals(user.getRole(), "teacher")){
                    startActivity(intentuser);
                }
                else{
                    warningTextView.setText("erreur impossible");
                    System.out.println("erreur impossible"+user);
                    warningTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}