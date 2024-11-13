package tn.esprit.espritclubs.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Club {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    private int imageResId;  // Change this to int to accept the image resource ID

    // Constructor
    public Club(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;  // Set the image resource ID
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for imageResId
    public int getImageResId() {
        return imageResId;  // Return the image resource ID
    }

    // Setter for imageResId
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
