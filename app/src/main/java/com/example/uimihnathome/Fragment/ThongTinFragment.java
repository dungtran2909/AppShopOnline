package com.example.uimihnathome.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.model.User;
import com.example.network.ApiService;
import com.example.uimihnathome.ChiTietThongTinActivity;
import com.example.uimihnathome.LoginActivity;
import com.example.uimihnathome.MainActivity;
import com.example.uimihnathome.R;
import com.example.uimihnathome.SanPhamYeuThichActivity;
import com.example.uimihnathome.SupportCenterActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinFragment extends Fragment implements OnMapReadyCallback {
    View view;
    public static LatLng latLng;
    public static String cityName;

    TextView txt_setting, txt_signOut, txt_support, txt_like, txt_tenUser;

    private GoogleMap mMap;
    GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            latLng = new LatLng(location.getLatitude(),
                    location.getLongitude());
            if(mMap != null){
                mMap.clear();
                Marker marker = mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("")
                                .snippet(""));
                marker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thongtin, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        txt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ChiTietThongTinActivity.class);
                startActivity(intent);

            }
        });

        txt_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        txt_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SupportCenterActivity.class);
                startActivity(intent);
            }
        });

//        txt_like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view.getContext(), SanPhamYeuThichActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    private void getInfo(){
        ApiService.getInstance().getUserTheoEmail(MainActivity.khs.getEmail(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User kh = response.body();
                    txt_tenUser.setText(kh.getTenKH());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void addControls() {
        txt_setting = view.findViewById(R.id.txt_setting);
        txt_signOut = view.findViewById(R.id.txt_signOut);
        txt_support = view.findViewById(R.id.txt_support);
//        txt_like = view.findViewById(R.id.txt_like);
        txt_tenUser = view.findViewById(R.id.txt_tenUser);

       getInfo();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
//            @Override
//            public void onMapLoaded() {
//                LatLng topica = new LatLng(10.772052, 106.666417);
//                mMap.addMarker(new MarkerOptions()
//                        .position(topica)
//                        .title("Topica").snippet("To hop cong nghe giao duc"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(topica, 13));
//            }
//        });

        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(onMyLocationChangeListener);
    }
}
