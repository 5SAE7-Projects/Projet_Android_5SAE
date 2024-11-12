package tn.esprit.espritclubs.activities.bacemActivities.Module;

public class ToDoModle {
    private String task;
    private int id,status;
    private boolean isDone;

    public ToDoModle() {

    }

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

    public ToDoModle(String task, int id, int status){
        this.task=task;
        this.id=id;
        this.status=status;
    }

    public String getTask() {
        return this.task;
    }
}
