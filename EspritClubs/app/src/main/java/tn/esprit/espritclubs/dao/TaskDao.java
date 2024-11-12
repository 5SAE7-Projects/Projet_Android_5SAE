package tn.esprit.espritclubs.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.espritclubs.entities.Task;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insertTask(Task task);
}
