package geoalarmapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import geoalarmapp.data.dao.AlarmDao;
import geoalarmapp.data.entity.Alarm;

//TODO: Implement migration strategy for schema changes in the future

@Database(entities = {Alarm.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlarmDao alarmDao();

    public static volatile AppDatabase INSTANCE;
    static AppDatabase getDatabase(final Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "app_database").build();
            }
        }
        return INSTANCE;
    }
}
