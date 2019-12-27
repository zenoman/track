package com.t.podomorotrack.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.t.podomorotrack.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Map_Place extends FragmentActivity {
    Location cuLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private  static  final int REQUEST_CODE = 101;
    GoogleMap googleMap;
    LinearLayout layoutSheet;
    BottomSheetBehavior sheetBehavior;
    private TextView lokasi,koordinat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__place);
        layoutSheet=(LinearLayout) findViewById(R.id.bottom_sheet);
        lokasi=(TextView) findViewById(R.id.place);
        koordinat=(TextView) findViewById(R.id.koordinat);
        sheetBehavior=BottomSheetBehavior.from(layoutSheet);
        sheetBehavior.setHideable(true);
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchingLoc();
    }

    private void fetchingLoc() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        //Get Location name
       final Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null){
                    cuLocation=location;
                   final double lat=cuLocation.getLatitude();
                    final double lot=cuLocation.getLongitude();
                    try {
                       List<Address> addresses=geocoder.getFromLocation(lat,lot,1);
                        final String cityName = addresses.get(0).getAddressLine(0);
                        final String stateName = addresses.get(0).getAddressLine(1);
                        final String countryName = addresses.get(0).getAddressLine(2);
                        lokasi.setText(cityName + ", " + stateName + ", "+ countryName);
                        koordinat.setText(lat +" , " + lot);
                    SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMap);
                    assert  supportMapFragment !=null;
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(cuLocation.getLatitude(), cuLocation.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!"+ String.valueOf(lat)+ " , " + String.valueOf(lot)+" "+cityName + "," +stateName);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            googleMap.setMyLocationEnabled(true);
                            googleMap.addMarker(markerOptions);

                        }
                    });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchingLoc();
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
