package geoalarmapp.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import geoalarmapp.data.entity.Alarm;

@Dao
public interface AlarmDao {
    @Query("SELECT * FROM alarm_table")
    LiveData<List<Alarm>> getAlarms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Alarm alarm);
}
