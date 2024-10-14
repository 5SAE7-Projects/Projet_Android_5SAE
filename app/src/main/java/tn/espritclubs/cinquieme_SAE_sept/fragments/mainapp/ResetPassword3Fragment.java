package tn.espritclubs.cinquieme_SAE_sept.fragments.mainapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.models.User;
import tn.espritclubs.cinquieme_SAE_sept.services.SQLiteUserManager;


public class ResetPassword3Fragment extends Fragment {
    private Button confirm_button;
    private EditText newPassword, confirmPassword;
    private TextView backToSignIn, warningTextpassword, warningTextconfirmpassword;
    private User usertoupdate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password3, container, false);

        Bundle bundle1 = getArguments();
        if (bundle1 != null) {
            int idUser = bundle1.getInt("userid");
            usertoupdate = User.getUserForId(idUser);
        }

        confirm_button = view.findViewById(R.id.confirm_button);
        newPassword = view.findViewById(R.id.newPassword);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        backToSignIn = view.findViewById(R.id.backToSignIn);
        warningTextpassword = view.findViewById(R.id.warningTextpassword);
        warningTextconfirmpassword = view.findViewById(R.id.warningTextconfirmpassword);




        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = newPassword.getText().toString().trim();
                if (password.length() < 8) {
                    confirm_button.setEnabled(false);
                    warningTextpassword.setVisibility(View.VISIBLE);
                    warningTextpassword.setText("Password must be at least 8 characters");
                }else
                {
                    warningTextpassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = newPassword.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();
                if (!password.equals(confirm)) {
                    confirm_button.setEnabled(false);
                    warningTextconfirmpassword.setVisibility(View.VISIBLE);
                    warningTextconfirmpassword.setText("Passwords do not match");
                }else
                {
                    confirm_button.setEnabled(true);
                    warningTextconfirmpassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        backToSignIn.setOnClickListener(houssem->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.main_fragment_container, new LogInFragment()).commit();
        });
        confirm_button.setOnClickListener(houssem->{
            String password = newPassword.getText().toString().trim();
            System.out.println(usertoupdate.toString());
            usertoupdate.setPassword(password);
            System.out.println(usertoupdate.toString());
            SQLiteUserManager sqLiteManager = SQLiteUserManager.instanceOfDatabase(getContext());
            sqLiteManager.updateUserInDatabase(usertoupdate);
            Toast.makeText(getActivity(), "Password updated successfully", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.main_fragment_container, new LogInFragment()).commit();
        });
        return view;
    }
}