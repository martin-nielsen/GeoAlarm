package geoalarmapp.activity.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import geoalarmapp.data.dao.AlarmDao;
import geoalarmapp.data.db.AppDatabase;
import geoalarmapp.data.entity.Alarm;

public class AlarmViewModel extends AndroidViewModel {

    private final LiveData<List<Alarm>> mAlarmList;
    private AlarmDao mAlarmDao;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAlarmDao = db.alarmDao();
        mAlarmList = mAlarmDao.getAlarms();
    }

    LiveData<List<Alarm>> getAllAlarms() { return mAlarmList; }

    public void insertAlarm(Alarm alarm) { mAlarmDao.insert(alarm); }
}
