package tn.esprit.espritclubs.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    private String messageComposed;
    public Message(){

    }
    public Message(String messageComposed){
        this.messageComposed = messageComposed;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageComposed() {
        return messageComposed;
    }

    public void setMessageComposed(String messageComposed) {
        this.messageComposed = messageComposed;
    }
}
