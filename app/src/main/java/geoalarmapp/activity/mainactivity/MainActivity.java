package geoalarmapp.activity.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import geoalarmapp.R;
import geoalarmapp.activity.CreateAlarmActivity;
import geoalarmapp.activity.PermissionHandler;

public class  MainActivity extends AppCompatActivity {
    private AlarmViewModel mAlarmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpAlarmList();
        setUpAlarmButton();
    }

    private void setUpAlarmList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AlarmListAdapter adapter = new AlarmListAdapter(new AlarmListAdapter.AlarmDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAlarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);
        mAlarmViewModel.getAllAlarms().observe(this, alarms -> {
            adapter.submitList(alarms);
        });
    }

    private void setUpAlarmButton() {
        FloatingActionButton addAlarmButton = findViewById(R.id.floatingActionButton);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionHandler.checkAndRequestLocationPermission(MainActivity.this) == true) {
                    Intent createAlarmActivityIntent = new Intent(getApplicationContext(), CreateAlarmActivity.class);
                    startActivity(createAlarmActivityIntent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionHandler.onRequestResults(this, requestCode, permissions, grantResults);
    }

}