package tn.esprit.espritclubs.dao;
import androidx.room.*;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

import tn.esprit.espritclubs.entities.Event;
import tn.esprit.espritclubs.entities.User;


@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAll();


    @Delete
    void delete(Event event);


    @Insert
    void  insertEvent(Event event);
}
