package com.example.jasmine.progettoinfo3;
import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaration
    private TextView text;
    Location location;
    double longitude = 0;
    double latitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        text=(TextView) findViewById(R.id.txt);
        //location
        aggiornaPosix();

    }

    public void button_aggiornaPosix(View view){
        aggiornaPosix();
    }

    void aggiornaPosix(){

        try {
            LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            LocationListener mLocListener = new MyLocationListener();
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);

            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            boolean networkEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if(networkEnabled){
                longitude=location.getLongitude(); // E
                latitude=location.getLatitude(); // N
            }
            text.setText("lon E: " + Double.toString(longitude)  +"\nlat N: " + Double.toString(latitude));
            Toast.makeText(this, "Posizione aggiornata", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e){

            text.setText("Errore");
        }
    }
    public class MyLocationListener implements LocationListener{

        public void onLocationChanged(Location loc) {
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
        public void onProviderDisabled(String arg0) {

        }
        public void onProviderEnabled(String provider) {

        }
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

}



