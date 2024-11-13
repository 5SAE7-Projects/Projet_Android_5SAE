package tn.esprit.espritclubs.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String userEmail;
    public String userName;
    public long reservationDate;
    public String clubName;
      // Store date as a timestamp for simplicity

    public Reservation() {
    }

    public Reservation(String email, String name, long reservationdate, String clubName) {
        this.userEmail = email;
        this.userName = name;
        this.reservationDate = reservationdate;
        this.clubName = clubName;
    }


        public int getId () {
            return id;
        }

        public void setId ( int id){
            this.id = id;
        }

        public String getEmail () {
            return userEmail;
        }

        public void setEmail (String email){
            this.userEmail = email;
        }

        public String getName () {
            return userName;
        }

        public void setName (String name){
            this.userName = name;
        }

        public long getReservationDate () {
            return reservationDate;
        }

        public void setReservationDate ( long reservationDate){
            this.reservationDate = reservationDate;
        }

        public String getClubName () {
            return clubName;
        }

        public void setClubName (String clubName){
            this.clubName = clubName;
        }

    }


