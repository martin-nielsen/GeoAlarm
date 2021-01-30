package geoalarmapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.search.MapboxSearchSdk;
import com.mapbox.search.location.DefaultLocationProvider;
import com.mapbox.search.result.SearchResult;
import com.mapbox.search.ui.view.SearchBottomSheetView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import geoalarmapp.R;
import geoalarmapp.activity.mainactivity.AlarmViewModel;
import geoalarmapp.data.entity.Alarm;

public class CreateAlarmActivity extends AppCompatActivity {
    private Alarm mAlarm;
//    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionHandler.checkAndRequestLocationPermission(this);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));


        setContentView(R.layout.activity_create_alarm);
        Application app = getApplication();
        MapboxSearchSdk.initialize(
                app,
                getString(R.string.mapbox_access_token),
                new DefaultLocationProvider(app)
        );
        this.initLocationSearch(savedInstanceState);

        AlarmViewModel avm = new ViewModelProvider(this).get(AlarmViewModel.class);

        Button saveAlarmButton = findViewById(R.id.saveAlarmButton);
        saveAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO extract coordinates and location name
                Toast.makeText(CreateAlarmActivity.this, "Clicked save", Toast.LENGTH_SHORT).show();
                avm.insertAlarm(mAlarm);
            }
        });

    }

    private void initLocationSearch(Bundle savedInstanceState) {
        SearchBottomSheetView searchBottomSheetView = findViewById(R.id.search_view);
        searchBottomSheetView.initializeSearch(savedInstanceState, new SearchBottomSheetView.Configuration());
        PermissionHandler.checkAndRequestLocationPermission(this);

        initSearchListener(searchBottomSheetView);
    }

    private void initSearchListener(SearchBottomSheetView searchBottomSheetView) {
            searchBottomSheetView.addOnSearchResultClickListener(new SearchBottomSheetView.OnSearchResultClickListener() {

                @Override
                public void onSearchResultClick(@NotNull SearchResult searchResult) {
                    Log.d("Search results", searchResult.getName());
                    List<Double> coordinates = searchResult.getCoordinate().coordinates();
                    mAlarm = new Alarm();
                    mAlarm.name = searchResult.getName();
                    mAlarm.latitude = coordinates.get(0);
                    mAlarm.longitude = coordinates.get(1);
                }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionHandler.onRequestResults(this, requestCode, permissions, grantResults);
    }
}