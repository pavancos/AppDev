package com.example.map8;
import androidx.fragment.app.FragmentActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.map8.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng home = new LatLng(16.52713238913416, 80.64272858009313);
        LatLng college = new LatLng(16.488856, 80.694021);
        mMap.addMarker(new MarkerOptions().position(home).title("Marker in Vijayawada"));
        mMap.addMarker(new MarkerOptions().position(college).title("Marker in College"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 15));
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}