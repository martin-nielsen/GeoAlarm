package geoalarmapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import geoalarmapp.R;
import geoalarmapp.activity.mainactivity.AlarmViewModel;
import geoalarmapp.data.entity.Alarm;

public class CreateAlarmActivity extends AppCompatActivity {
    private EditText mXcoordinateField;
    private EditText mYcoordinateField;
    private EditText mAlarmNameField;
    private Alarm mAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
        AlarmViewModel avm = new ViewModelProvider(this).get(AlarmViewModel.class);

        this.setUpFormFields();

        Button saveAlarmButton = findViewById(R.id.saveAlarmButton);
        saveAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Validate and save alarm here
                Toast.makeText(CreateAlarmActivity.this, "Clicked save", Toast.LENGTH_SHORT).show();
                mAlarm = new Alarm();
                mAlarm.name = mAlarmNameField.getText().toString();
                mAlarm.xCoord = Float.parseFloat(mXcoordinateField.getText().toString().trim());
                mAlarm.yCoord = Float.parseFloat(mYcoordinateField.getText().toString().trim());
                avm.insertAlarm(mAlarm);
            }
        });
    }

    private void setUpFormFields() {
        mXcoordinateField = (EditText) findViewById(R.id.xCoordinate);
        mYcoordinateField = (EditText) findViewById(R.id.yCoordinate);
        mAlarmNameField = (EditText) findViewById(R.id.editTextAlarmName);
    }
}