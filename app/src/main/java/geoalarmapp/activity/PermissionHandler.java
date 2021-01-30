package geoalarmapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class PermissionHandler {
    private static int FINE_LOCATION_PERMISSION_CODE = 1;

    public static void checkAndRequestLocationPermission(AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // TODO: Remove this dialog as it is uneccessary but good for dev purposes
            Toast.makeText(activity, "Permission already acquired!", Toast.LENGTH_SHORT).show();

            Intent createAlarmActivityIntent = new Intent(activity.getApplicationContext(), CreateAlarmActivity.class);
            activity.startActivity(createAlarmActivityIntent);
        } else {
            // if no permission, request it.
            requestLocationPermission(activity);
        }
    }

    private static void requestLocationPermission(AppCompatActivity activity) {
        if (activity.shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(activity)
                    .setTitle("Location Permission Needed")
                    .setMessage("This is needed to use location based alarms efficiently.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity,
                                    new String[] {ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[] {ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_CODE);
        }
    }

    public static void onRequestResults(AppCompatActivity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)  {
        if (requestCode == FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
