package com.example.uimihnathome;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.SanPhamFirebase;
import com.example.impl.ButtonClickListener;
import com.example.model.SanPham;
import com.example.uimihnathome.Dialog.DialogBottomSheetBuy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ThongTinSanPhamActivity extends AppCompatActivity {

    TextView txt_buy_bottom, txt_add_bottom, txt_nametheme;
    ImageView img_back, img_like;
    ImageView img_hinhSP;

    ImageView img_subtract, img_plus;
    TextView txt_number;

    SanPham sp = new SanPham();

    DatabaseReference mData;

    Button btn_review;
    TextView txt_tenSP, txt_giaSP, txt_slSP, txt_motaSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_san_pham);
        firebaseData();
        Intent intentSP = getIntent();
        sp = (SanPham) intentSP.getSerializableExtra("SANPHAM");

        addControls();
        addEvents();
    }

    private void firebaseData() {
        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void addEvents() {
        img_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getSlSP() > 0) {
                    img_subtract.setBackgroundColor(ContextCompat.getColor(ThongTinSanPhamActivity.this, R.color.color_white));
                    sp.setSlSP(Integer.parseInt(txt_number.getText().toString()) - 1);
                } else {
                    img_subtract.setBackgroundColor(ContextCompat.getColor(ThongTinSanPhamActivity.this, R.color.grey_enable_false));
                }
                txt_number.setText(sp.getSlSP() + "");
            }
        });

        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getSlSP() < 100) {
                    img_subtract.setBackgroundColor(ContextCompat.getColor(ThongTinSanPhamActivity.this, R.color.color_white));
                    sp.setSlSP(Integer.parseInt(txt_number.getText().toString()) + 1);
                } else {
                    img_plus.setBackgroundColor(ContextCompat.getColor(ThongTinSanPhamActivity.this, R.color.grey_enable_false));
                }
                txt_number.setText(sp.getSlSP() + "");
            }
        });

        txt_add_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThongTinSanPhamActivity.this, ChiTietGioHangActivity.class);
                sp.setSlSP(Integer.parseInt(txt_number.getText().toString()));
                if (sp.getSlSP() == 0) {
                    Toast.makeText(ThongTinSanPhamActivity.this, "Hãy chọn số lượng mua hàng", Toast.LENGTH_SHORT).show();
                } else {
                    addSpFirebase();
                    startActivity(intent);
                }

            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        ButtonClickListener buttonClickListener = new ButtonClickListener() {
//            @Override
//            public void Click() {
//                Intent intent = new Intent(ThongTinSanPhamActivity.this, ChiTietGioHangActivity.class);
//                startActivity(intent);
//            }
//        };

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham spTruyen = sp;
                Intent intent = new Intent(ThongTinSanPhamActivity.this, VideoReviewActivity.class);
                intent.putExtra("SPTruyen", spTruyen);
                startActivity(intent);
            }
        });

    }

    private void addSpFirebase(){
        SanPhamFirebase sanPhamFirebase = new SanPhamFirebase();
        sanPhamFirebase.setMaSP(sp.getMaSP());
        sanPhamFirebase.setTenSP(sp.getTenSP());
        sanPhamFirebase.setGiaSP(sp.getDonGia());
        sanPhamFirebase.setHinhSP(sp.getHinhSP());
        sanPhamFirebase.setSlSP(sp.getSlSP());
        mData.child("GioHang").child(MainActivity.khs.getMaKH()+"").child(sanPhamFirebase.getMaSP()+"").setValue(sanPhamFirebase);

    }


    private void addControls() {

        txt_add_bottom = findViewById(R.id.txt_add_bottom);
        img_back = findViewById(R.id.img_back);
//        img_like = findViewById(R.id.img_like);

        txt_nametheme = findViewById(R.id.txt_nametheme);
        txt_tenSP = findViewById(R.id.txt_tenSP);
        txt_giaSP = findViewById(R.id.txt_giaSP);
        img_hinhSP = findViewById(R.id.img_hinhSP);
        txt_slSP = findViewById(R.id.txt_slSP);
        txt_motaSP = findViewById(R.id.txt_motaSP);

        img_subtract = findViewById(R.id.img_subtract);
        img_plus = findViewById(R.id.img_plus);
        txt_number = findViewById(R.id.txt_number);

        txt_nametheme.setText(sp.getTenSP());
        txt_tenSP.setText(sp.getTenSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        txt_giaSP.setText(numberFormat.format(sp.getDonGia()) + " VNĐ");
        Picasso.with(ThongTinSanPhamActivity.this).load(sp.getHinhSP()).into(img_hinhSP);
//        img_hinhSP.setImageResource(sp.getHinhSP());
        txt_slSP.setText(sp.getSoLuongTon() + "");
        txt_motaSP.setText(sp.getMoTa());

        btn_review = findViewById(R.id.btn_review);
    }

}
