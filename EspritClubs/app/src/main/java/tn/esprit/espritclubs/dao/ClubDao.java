package tn.esprit.espritclubs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import tn.esprit.espritclubs.entities.Club;
import java.util.List;

@Dao
public interface ClubDao {
    @Query("SELECT * FROM Club")
    List<Club> getAllClubs();

    @Insert
    void insertClub(Club club);

    @Query("SELECT * FROM club")
    LiveData<List<Club>> getAllClubsLive();
}
