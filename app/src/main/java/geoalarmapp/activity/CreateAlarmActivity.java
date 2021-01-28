package geoalarmapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import geoalarmapp.R;
import geoalarmapp.activity.mainactivity.AlarmViewModel;
import geoalarmapp.data.entity.Alarm;

public class CreateAlarmActivity extends AppCompatActivity {
    private EditText mXcoordinateField;
    private EditText mYcoordinateField;
    private EditText mAlarmNameField;
    private Alarm mAlarm;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_create_alarm);

        this.setUpMap(savedInstanceState);

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

    private void setUpMap(Bundle savedInstanceState) {
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}