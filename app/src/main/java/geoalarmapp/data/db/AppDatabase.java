package geoalarmapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import geoalarmapp.data.dao.AlarmDao;
import geoalarmapp.data.entity.Alarm;

@Database(entities = {Alarm.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlarmDao alarmDao();
}
