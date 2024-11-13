package tn.esprit.espritclubs.database;

import android.content.Context;

import androidx.room.*;

import java.util.concurrent.*;

import tn.esprit.espritclubs.dao.EventDao;
import tn.esprit.espritclubs.dao.UserDao;
import tn.esprit.espritclubs.entities.Event;
import tn.esprit.espritclubs.entities.User;
import tn.esprit.espritclubs.utils.Converters;

@Database(entities = {User.class, Event.class,Task.class, Message.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract EventDao eventDao();
    public abstract TaskDao taskDao();
    public abstract MessageDao messageDao();


    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
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
