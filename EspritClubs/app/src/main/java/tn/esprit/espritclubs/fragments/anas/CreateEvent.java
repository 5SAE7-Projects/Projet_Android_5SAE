package tn.esprit.espritclubs.fragments.anas;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Event;


public class CreateEvent extends Fragment {
    private EditText editTextDescription , editTextEventDate, editTextMaxCapacity;
    private Button buttonPickImage, buttonSubmit;
    private ImageView imageViewPreview;
    private Uri imageUri;
    private static final int PROFILE_PIC_REQUEST_CODE = 1001;
    private Event newEvent;


    private AppDatabase appDatabase;

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newEvent = new Event();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextMaxCapacity = view.findViewById(R.id.editTextMaxCapacity);
        editTextEventDate = view.findViewById(R.id.editTextEventDate);
        buttonPickImage = view.findViewById(R.id.buttonPickImage);
        imageViewPreview = view.findViewById(R.id.imageViewPreview);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        appDatabase= AppDatabase.getDatabase(getContext());

        buttonSubmit.setOnClickListener(e -> {
            String description = editTextDescription.getText().toString().trim();
            //String date = editTextEventDate.getText().toString().trim();
            int maxCapacity = Integer.parseInt(editTextMaxCapacity.getText().toString().trim());
            Date date = getDateFromString(editTextEventDate.getText().toString().trim());
            //Event event = new Event(description, date, maxCapacity);
            newEvent.setEventDate(date);
            newEvent.setDescription(description);
            newEvent.setMaxCapacity(maxCapacity);
            appDatabase.eventDao().insertEvent(newEvent);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_content, new AnasFragment()).commit();


        });


        buttonPickImage.setOnClickListener(e -> {
            ImagePicker.with(this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start(PROFILE_PIC_REQUEST_CODE);  // Include request code here

        });



        return view;
    }

    private Date getDateFromString(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }

    private String getStringFormDate(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                if (requestCode == PROFILE_PIC_REQUEST_CODE) {
                    imageViewPreview.setImageURI(selectedImageUri);
                    imageViewPreview.setVisibility(View.VISIBLE);
                    newEvent.setEventPicture(selectedImageUri.toString());
                    //appDatabase.eventDao().updateUser(currentUser);

                    Toast.makeText(getContext(), "Event picture updated!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}