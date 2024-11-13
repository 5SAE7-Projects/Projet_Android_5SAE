package tn.esprit.espritclubs.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    public static final List<User> userArrayList = new ArrayList<User>();

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    private int eventId;

    @ColumnInfo(name = "event_picture")
    private String eventPicture;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "event_date")
    private Date eventDate;

    @ColumnInfo(name = "max_capacity")
    private int maxCapacity;

    @ColumnInfo(name = "nbr_participant")
    private int nbrParticipant;

    public Event() {
    }

    public Event(String description, Date eventDate, int maxCapacity) {
        this.description = description;
        this.eventDate = eventDate;
        this.maxCapacity = maxCapacity;
        this.nbrParticipant = 0;
    }

    public Event(String eventPicture, String description, Date eventDate, int maxCapacity) {
        this.eventPicture = eventPicture;
        this.description = description;
        this.nbrParticipant = 0;
        this.eventDate = eventDate;
        this.maxCapacity = maxCapacity;
    }

    public Event(int eventId, String eventPicture, String description, Date eventDate, int maxCapacity) {
        this.eventId = eventId;
        this.eventPicture = eventPicture;
        this.description = description;
        this.eventDate = eventDate;
        this.maxCapacity = maxCapacity;
        this.nbrParticipant = 0;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getNbrParticipant() {
        return nbrParticipant;
    }

    public void setNbrParticipant(int nbrParticipant) {
        this.nbrParticipant = nbrParticipant;
    }
}
