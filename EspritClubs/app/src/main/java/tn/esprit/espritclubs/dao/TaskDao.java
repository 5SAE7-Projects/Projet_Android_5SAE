package tn.esprit.espritclubs.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.espritclubs.entities.Task;
import tn.esprit.espritclubs.entities.User;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insertTask(Task task);

    @Delete
    void delete(Task task);
    @Query("SELECT * FROM task WHERE id = :id")
    Task getTaskByTid(Long id);
    @Update
    void updateTask(Task task);
}
