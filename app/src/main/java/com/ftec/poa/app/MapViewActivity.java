package com.ftec.poa.app;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap gMap;
    private CameraPosition position;
    private int index = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        index = getIntent().getIntExtra("index",-1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if(index == -1){
            for(int i = 0; i < MainActivity.locations.size();i++){
                Location l = MainActivity.locations.get(i);
                if(i == 0){
                    position = new CameraPosition(new LatLng(l.getLati(),l.getLongi()),15,0,0);
                    CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
                    gMap.animateCamera(update);
                }
                int height = 60;
                int width = 60;
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(getDrawable(l.getType()));
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                gMap.addMarker(new MarkerOptions().position(new LatLng(l.getLati(),l.getLongi())).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .title(l.getPlaceName()).snippet(l.getAddressName()).draggable(false));
            }
        }else{
            Location l = MainActivity.locations.get(index);
            position = new CameraPosition(new LatLng(l.getLati(),l.getLongi()),15,0,0);
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            gMap.animateCamera(update);
            int height = 60;
            int width = 60;
            BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(getDrawable(l.getType()));
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            gMap.addMarker(new MarkerOptions().position(new LatLng(l.getLati(),l.getLongi())).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                    .title(l.getPlaceName()).snippet(l.getAddressName()).draggable(false));
        }

        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.getUiSettings().setMyLocationButtonEnabled(true);
    }
    private int getDrawable(String type){
        switch (type){
            case "Hospital":return R.drawable.hospital;
            case "Restaurante":return R.drawable.restaurant;
            case "Escritorio":return R.drawable.office;
            case "Escola":return R.drawable.school;
            case "Parque":return  R.drawable.park;
            default: return R.mipmap.ic_launcher_round;
        }
    }
}
