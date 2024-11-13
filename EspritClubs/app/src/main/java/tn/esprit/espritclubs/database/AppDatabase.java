package tn.esprit.espritclubs.database;

import android.content.Context;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import tn.esprit.espritclubs.dao.UserDao;
import tn.esprit.espritclubs.dao.ClubDao;
import tn.esprit.espritclubs.dao.ReservationDao;
import tn.esprit.espritclubs.entities.User;
import tn.esprit.espritclubs.entities.Club;
import tn.esprit.espritclubs.entities.Reservation;
import tn.esprit.espritclubs.utils.Converters;

import java.util.concurrent.*;

import tn.esprit.espritclubs.dao.EventDao;
import tn.esprit.espritclubs.dao.MessageDao;
import tn.esprit.espritclubs.dao.TaskDao;
import tn.esprit.espritclubs.dao.UserDao;
import tn.esprit.espritclubs.entities.Event;
import tn.esprit.espritclubs.entities.Message;
import tn.esprit.espritclubs.entities.Task;
import tn.esprit.espritclubs.entities.User;
import tn.esprit.espritclubs.utils.Converters;

@Database(entities = {User.class, Event.class, Task.class, Message.class,Reservation.class, Club.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract EventDao eventDao();
    public abstract TaskDao taskDao();
    public abstract MessageDao messageDao();
    public abstract ReservationDao reservationDao();
    public abstract ClubDao clubDao();


    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static final Migration MIGRATION_2_3= new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {



        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Add migration to the database builder
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "clubs_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
