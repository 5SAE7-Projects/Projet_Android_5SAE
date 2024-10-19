package tn.espritclubs.cinquieme_SAE_sept.fragments.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.activities.UserActivity;
import tn.espritclubs.cinquieme_SAE_sept.models.User;
import tn.espritclubs.cinquieme_SAE_sept.services.SQLiteUserManager;


public class ProfileFragment extends Fragment {

    private TextView emailTextView, firstNameTextView, lastNameTextView, genderTextView, roleTextView, birthdayTextView, phoneNumberTextView, aboutMeTextView, changeProfilePic;
    private String email, firstName, lastName, gender, role, birthday, phoneNumber, aboutMe;
    private int userId;
    private User currentUser;
    private ImageView profileImageView;
    private Button editInfoButton, editPasswordButton;

    private Uri imageUri;
    SQLiteUserManager dbHelper;

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

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


        dbHelper = new SQLiteUserManager(getContext());

        changeProfilePic.setOnClickListener(houssem->{
            ImagePicker.with(this)  // Use 'this' for Fragment
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start();
        });

        editInfoButton.setOnClickListener(houssem->{
            Intent intent = new Intent(getContext(), UserActivity.class);
            intent.putExtra("userid", userId);
            startActivity(intent);

             });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);  // Always call the super method
        if (resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                profileImageView.setImageURI(imageUri);
                System.out.println("before automatique");
                System.out.println(currentUser.getProfilePicture().toString());
                // Save the image URI to the database immediately
                currentUser.setProfilePicture(imageUri.toString());
                dbHelper.updateimageUserInDatabase(currentUser);
                System.out.println("after automatique");
                System.out.println(currentUser.getProfilePicture().toString());

                // Provide feedback to the user
                Toast.makeText(getContext(), "Profile picture updated!", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }



    private void loadProfileImage() {
        String profileImageUri = currentUser.getProfilePicture();
        if (profileImageUri != null && !profileImageUri.isEmpty()) {
            profileImageView.setImageURI(Uri.parse(profileImageUri));
        } else {
            // Optionally set a default image if no profile image is found
            profileImageView.setImageResource(R.drawable.ic_people);  // Use a default image here
        }
    }



    private void fillthetext() {
        emailTextView.setText(currentUser.getEmail());
        firstNameTextView.setText(currentUser.getFirstname());
        lastNameTextView.setText(currentUser.getLastname());
        genderTextView.setText(currentUser.getGender());
        roleTextView.setText(currentUser.getRole());
        birthdayTextView.setText(getStringFormDate(currentUser.getBirthday()));
        phoneNumberTextView.setText(currentUser.getPhoneNumber());
        aboutMeTextView.setText(currentUser.getAboutMe());

        loadProfileImage();

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
        changeProfilePic = view.findViewById(R.id.changeProfilePic);
        editInfoButton = view.findViewById(R.id.editInfoButton);
        editPasswordButton = view.findViewById(R.id.editPasswordButton);
    }

    private String getStringFormDate(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }
}