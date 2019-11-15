package com.example.uimihnathome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.model.User;
import com.example.uimihnathome.Fragment.DanhMucFragment;
import com.example.uimihnathome.Fragment.HomeFragment;
import com.example.uimihnathome.Fragment.ThongBaoFragment;
import com.example.uimihnathome.Fragment.ThongTinFragment;

public class MainActivity extends AppCompatActivity {
    public static User khs;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    return true;
                case R.id.nav_danhmuc:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DanhMucFragment()).commit();
                    return true;
                case R.id.nav_thongbao:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThongBaoFragment()).commit();
                    return true;
                case R.id.nav_thongtin:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThongTinFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        Intent intent = getIntent();
        khs = (User) intent.getSerializableExtra("KHACHHANG");
    }

}
