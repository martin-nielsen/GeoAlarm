package geoalarmapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import geoalarmapp.data.dao.AlarmDao;
import geoalarmapp.data.db.AppDatabase;
import geoalarmapp.data.entity.Alarm;

public class AlarmRepository {
    private final AppDatabase mDatabase;
    private static AlarmRepository sInstance;
    private AlarmDao mAlarmDao;
    private MediatorLiveData<List<Alarm>> mObservableAlarms;

    private AlarmRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableAlarms = new MediatorLiveData<>();
        mAlarmDao = mDatabase.alarmDao();

        mObservableAlarms.addSource(mDatabase.alarmDao().getAlarms(),
                alarmEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableAlarms.postValue(alarmEntities);
                    }
                });
    }

    public static AlarmRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (AlarmRepository.class) {
                if (sInstance == null) {
                    sInstance = new AlarmRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of alarms from the database and get notified when the data changes.
     */
    public LiveData<List<Alarm>> getAlarms() {
        return mObservableAlarms;
    }

    public void insert(Alarm alarm) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mAlarmDao.insert(alarm);
        });
    }
}
