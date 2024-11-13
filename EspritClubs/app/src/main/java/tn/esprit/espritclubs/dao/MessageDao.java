package tn.esprit.espritclubs.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.espritclubs.entities.Message;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM task")
    List<Message> getAll();

    @Insert
    void insertTMessage(Message message);

    @Delete
    void delete(Message task);
    @Query("SELECT * FROM task WHERE id = :id")
    Message getMessageByTid(Long id);
    @Update
    void updateMessage(Message message);
}
