package com.example.android.actionbarcompat.basic;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener,GoogleMap.OnInfoWindowClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Map","running the map");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);




        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.412954, -119.846900))
                .title("Woodstock")
                .snippet("Points: 200")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.414865, -119.854882))
                .title("Blaze")
                .snippet("Points: 150")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));





    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("Map","the marker was clicked");

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("Map","infowindow clicked");
        Intent intent = new Intent(this,Resturant.class);
        intent.putExtra("resturantname",marker.getTitle());
        //String s = getIntent().getStringExtra("resturantname");
        startActivity(intent);

    }
}
