package tn.esprit.espritclubs.fragments.anas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Event;

public class EventList extends ArrayAdapter<Event> {
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    private  Uri imageUri;

    private AppDatabase appDatabase;


    public EventList(Context context, int ressourse, List<Event> object){
        super(context, ressourse, object);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_box, parent, false);
        }

        TextView description = convertView.findViewById(R.id.eventDescription);
        TextView date = convertView.findViewById(R.id.eventDate);
        ImageView eventImage = convertView.findViewById(R.id.eventImage);
        TextView delButton = convertView.findViewById(R.id.delButton);


        String profileImageUri = event.getEventPicture();

        if (profileImageUri != null && !profileImageUri.isEmpty()) {
            Uri imageUri = Uri.parse(profileImageUri);
            eventImage.setImageURI(imageUri);
            eventImage.setVisibility(View.VISIBLE);
            eventImage.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            eventImage.setVisibility(View.GONE);
            eventImage.getLayoutParams().height = 0; // Set height to zero to remove space
        }

        description.setText(event.getDescription());
        date.setText(getStringFormDate(event.getEventDate()));

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to delete this event
                removeEvent(event);
            }
        });

        return convertView;
    }
    public void removeEvent(Event event) {
        // Delete the event from the database
        appDatabase = AppDatabase.getDatabase(getContext());
        appDatabase.eventDao().delete(event);

        // Remove the event from the in-memory list and refresh the adapter
        remove(event); // Assuming `eventsList` is the list used by the adapter
        notifyDataSetChanged(); // Notify the adapter to refresh the list view
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

}
