package tn.espritclubs.cinquieme_SAE_sept.fragments.mainapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.activities.MainActivity;
import tn.espritclubs.cinquieme_SAE_sept.models.User;
import tn.espritclubs.cinquieme_SAE_sept.services.SQLiteUserManager;


public class SignUpFragment extends Fragment {

    private Button returnfromsignuptologin, signup;
    private TextView privacy_policy_text;
    private EditText email_field, first_name_field, last_name_field, password_field, confirm_password_field;
    private RadioGroup genderGroup;
    private String gender = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        getElementsByTheirIds(view);

        returnfromsignuptologin.setOnClickListener(houssem->{
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.main_fragment_container, new LogInFragment()).commit();

        });
        privacy_policy_text.setOnClickListener(houssem->{
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.main_fragment_container, new PolicyFragment()).commit();
        });
        signup.setOnClickListener(houssem -> {
            String email = email_field.getText().toString().trim();
            String firstname = first_name_field.getText().toString().trim();
            String lastname = last_name_field.getText().toString().trim();
            String password = password_field.getText().toString().trim();
            String confirmPassword = confirm_password_field.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter an email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (firstname.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter your first name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (lastname.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter your last name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter a password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = genderGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = view.findViewById(selectedId);
                gender = selectedRadioButton.getText().toString();
            } else {
                Toast.makeText(getActivity(), "Please select a gender", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteUserManager sqLiteManager = SQLiteUserManager.instanceOfDatabase(getContext());
            int id = User.userArrayList.size();
            User user = new User(id, email, firstname, lastname, gender, password, "user");
            sqLiteManager.addUserToDatabase(user);
            Toast.makeText(getActivity(), "User added to database", Toast.LENGTH_SHORT).show();
            System.out.println(User.userArrayList.size());
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.main_fragment_container, new LogInFragment()).commit();
        });
        
        return view;
    }

    private void getElementsByTheirIds(View view) {
        returnfromsignuptologin = view.findViewById(R.id.returnfromsignuptologin);
        signup = view.findViewById(R.id.btn_submit);
        email_field = view.findViewById(R.id.email_field);
        first_name_field = view.findViewById(R.id.first_name_field);
        last_name_field = view.findViewById(R.id.last_name_field);
        password_field = view.findViewById(R.id.password_field);
        confirm_password_field = view.findViewById(R.id.confirm_password_field);
        genderGroup = view.findViewById(R.id.gender_group);
        privacy_policy_text = view.findViewById(R.id.privacy_policy_text);
    }
}