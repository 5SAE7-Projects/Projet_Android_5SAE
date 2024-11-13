package tn.esprit.espritclubs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;
import tn.esprit.espritclubs.entities.Reservation;

@Dao
public interface ReservationDao {

    // Insert a new reservation
    @Insert
    void insertReservation(Reservation reservation);

    // Update an existing reservation
    @Update
    void updateReservation(Reservation reservation);

    // Delete a reservation
    @Delete
    void deleteReservation(Reservation reservation);

    @Query("SELECT * FROM Reservation")
    LiveData<List<Reservation>> getAllReservations();  // LiveData instead of List

    // Get a reservation by ID
    @Query("SELECT * FROM Reservation WHERE id = :reservationId")
    Reservation getReservationById(int reservationId);

    // Get reservations by club name
    @Query("SELECT * FROM Reservation WHERE clubName = :clubName")
    List<Reservation> getReservationsByClubName(String clubName);
}
