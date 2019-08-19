package com.example.uimihnathome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.CTDonHangAdapter;
import com.example.adapter.SanPhamAdapter;
import com.example.impl.ItemClickListener;
import com.example.model.DonHang;
import com.example.model.SanPham;
import com.example.model.User;
import com.example.network.ApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDonHangActivity extends AppCompatActivity {
    TextView txt_tenKH, txt_phoneKH, txt_diachiKH, txt_ngayDH;

    TextView txt_tamGia, txt_phiGia, txt_tongGia;

    ImageView img_back;

    Button btn_datHang;

    RecyclerView rcl_detail;
    ArrayList<SanPham> arrSP = new ArrayList<>();
    CTDonHangAdapter ctDonHangAdapter;
    DonHang don;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

        String listSP = getIntent().getStringExtra("LISTSP");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SanPham>>() {}.getType();
        arrSP = gson.fromJson(listSP, type);

        addControls();
        addEvents();
    }

    private void addEvents() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_datHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuDH();
                Toast.makeText(ChiTietDonHangActivity.this, "dadasdas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void xuLyLuuDH() {
        DonHang donHang = new DonHang();
        donHang.setMaKH(MainActivity.khs.getMaKH());
        donHang.setTrangThai(0);
        donHang.setNgayDat(txt_ngayDH.getText().toString());
        donHang.setNgayGiao(txt_ngayDH.getText().toString());
        int a = donHang.getMaKH();
        ApiService.getInstance().saveNewDonHang(donHang, new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("AAAAA", response.toString());
                if (response.isSuccessful()){
                    LayChiTietDonHang();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Error:", t.toString());
            }
        });
    }

    private void LayChiTietDonHang() {
        ApiService.getInstance().layDonHangTheoMaKH(MainActivity.khs.getMaKH(), new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                if(response.isSuccessful()){
                    ArrayList<DonHang> donHangs = (ArrayList<DonHang>) response.body();
                    don = new DonHang();
//                    for(DonHang donHang : donHangs){
//                        if (donHang.getNgayDat().equals(txt_ngayDH.getText().toString())){
//                            don.setMaDonHang(donHang.getMaDonHang());
//                        }
//                    }
                    int length = donHangs.size();
                    int MAX = donHangs.get(0).getMaDonHang();
                    for(int i=0; i<length; i++){
                        if(MAX < donHangs.get(i).getMaDonHang()){
                            MAX = donHangs.get(i).getMaDonHang();
                        }
                    }
                    don.setMaDonHang(MAX);
                    for(int i =0; i<arrSP.size(); i++){
                        SanPham sanPham = arrSP.get(i);
                        luuChiTietDonHang(don, sanPham);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {

            }
        });
    }

    private void luuChiTietDonHang(DonHang don, SanPham sanPham){
        ApiService.getInstance().luuCTDonHang(don.getMaDonHang(), sanPham.getMaSP(), sanPham.getSlSP(), new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    if (response.body()==true){
                        final AlertDialog.Builder builder= new AlertDialog.Builder(ChiTietDonHangActivity.this);
                        builder.setTitle("Đặt hàng thành công").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result",1);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                            }
                        }).show();
                        //Toast.makeText(NhapHangMoiActivity.this, "Lưu thành công", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(ChiTietDonHangActivity.this, "Lưu thất bại", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    private void getInfo() {
        ApiService.getInstance().getUserTheoEmail(MainActivity.khs.getEmail(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User kh = response.body();
                    txt_tenKH.setText(kh.getTenKH());
                    txt_phoneKH.setText("0" + kh.getPhone() + "");
                    txt_diachiKH.setText(kh.getDiaChi());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void addControls() {
        txt_tenKH = findViewById(R.id.txt_tenKH);
        txt_phoneKH = findViewById(R.id.txt_phoneKH);
        txt_diachiKH = findViewById(R.id.txt_diachiKH);
        txt_ngayDH = findViewById(R.id.txt_ngayDH);
        img_back = findViewById(R.id.img_back);
        btn_datHang = findViewById(R.id.btn_datHang);

        getInfo();
        txt_ngayDH.setText(simpleDateFormat.format(new Date(System.currentTimeMillis())));

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        };

        rcl_detail = findViewById(R.id.rcl_detail);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ChiTietDonHangActivity.this);
        rcl_detail.setLayoutManager(manager);
        rcl_detail.addItemDecoration(new DividerItemDecoration(ChiTietDonHangActivity.this, RecyclerView.VERTICAL));
        rcl_detail.setItemAnimator(new DefaultItemAnimator());
//        arrSP = new ArrayList<>();
        ctDonHangAdapter = new CTDonHangAdapter(ChiTietDonHangActivity.this, arrSP, itemClickListener);
        rcl_detail.setAdapter(ctDonHangAdapter);

        txt_tamGia = findViewById(R.id.txt_tamGia);
        txt_phiGia = findViewById(R.id.txt_phiGia);
        txt_tongGia = findViewById(R.id.txt_tongGia);

        int S = 0;
        int phi = 30000;
        int length = arrSP.size();
        for (int i = 0; i < length; i++) {
            S = S + arrSP.get(i).getSlSP() * arrSP.get(i).getDonGia();
        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        txt_tamGia.setText(numberFormat.format(S) + " VNĐ");
        txt_phiGia.setText(numberFormat.format(phi) + " VNĐ");
        txt_tongGia.setText(numberFormat.format((S + phi)) + " VNĐ");
    }
}
