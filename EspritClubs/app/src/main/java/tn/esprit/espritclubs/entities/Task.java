package tn.esprit.espritclubs.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    public Task() {

    }
    public Task(String task, int status){
        this.task=task;
        this.status=status;
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tid")
    private int id;
    private String task;
    private int status;
    private boolean isDone;

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
    public String getTask() {
        return this.task;
    }

}
