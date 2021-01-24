package geoalarmapp.activity.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import geoalarmapp.data.db.AppDatabase;
import geoalarmapp.data.entity.Alarm;
import geoalarmapp.data.repository.AlarmRepository;

public class AlarmViewModel extends AndroidViewModel {

    private final LiveData<List<Alarm>> mAlarmList;
    private AlarmRepository mRepository;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        // TODO: this could be improved to decouple database, app and repository further.
        mRepository = AlarmRepository.getInstance(AppDatabase.getDatabase(application));
        mAlarmList = mRepository.getAlarms();
    }

    LiveData<List<Alarm>> getAllAlarms() {
        return mAlarmList;
    }

    public void insertAlarm(Alarm alarm) {
        mRepository.insert(alarm);
    }
}
