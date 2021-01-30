package geoalarmapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;

public class PermissionHandler {
    private static int FINE_LOCATION_PERMISSION_CODE = 1;

    public static boolean checkAndRequestLocationPermission(AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(activity,
                    READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

            // TODO: Remove this dialog as it is uneccessary but good for dev purposes
            Toast.makeText(activity, "Permission already acquired!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // if no permission, request it.
            requestLocationPermission(activity);
            // TODO should instead be decided depending on dialog answer.
            return false;
        }
    }

    private static void requestLocationPermission(AppCompatActivity activity) {
        if (activity.shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(activity)
                    .setTitle("Location and phone state permission needed")
                    .setMessage("This is needed to use location based alarms efficiently.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity,
                                    new String[] {ACCESS_FINE_LOCATION, READ_PHONE_STATE}, FINE_LOCATION_PERMISSION_CODE);
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
                    new String[] {ACCESS_FINE_LOCATION, READ_PHONE_STATE}, FINE_LOCATION_PERMISSION_CODE);
        }
    }

    public static void onRequestResults(AppCompatActivity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)  {
        if (requestCode == FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 1) {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
