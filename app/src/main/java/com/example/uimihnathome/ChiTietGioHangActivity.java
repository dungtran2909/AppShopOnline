package com.example.uimihnathome;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.GioHangAdapter;
import com.example.firebase.SanPhamFirebase;
import com.example.impl.ButtonClickListener;
import com.example.impl.CheckClickListener;
import com.example.model.ChiTietDonHang;
import com.example.model.SanPham;
import com.example.model.User;
import com.example.network.ApiService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietGioHangActivity extends AppCompatActivity {
    RecyclerView rcl_giohang;
    GioHangAdapter gioHangAdapter;
    ArrayList<SanPham> arrSP;

    TextView txt_giaAll;
    Button btn_buyAll;

    ImageView img_back;
    SanPham sanPham;
    ArrayList<SanPhamFirebase> arrSpFirebase;

    DatabaseReference mData;

    ButtonClickListener buttonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_gio_hang);

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

//        btn_buyAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Gson gson = new Gson();
//                String jsonSP = gson.toJson(arrSP);
//                Intent intent = new Intent(ChiTietGioHangActivity.this, ChiTietDonHangActivity.class);
//                intent.putExtra("LISTSP",jsonSP );
//                startActivity(intent);
//            }
//        });

//        btn_buyAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String ten = MainActivity.khs.getTenKH();
//                String diachi = MainActivity.khs.getDiaChi();
//
//                if(MainActivity.khs.getDiaChi() == "" || MainActivity.khs.getTenKH() == "" || MainActivity.khs.getPhone()==0){
//                    showDialodTemp();
//                }else Toast.makeText(ChiTietGioHangActivity.this, "Mua hang thanh cong", Toast.LENGTH_SHORT).show();
//            }
//        });

        getUser();
    }

    private void getUser() {
        ApiService.getInstance().getUserTheoEmail(MainActivity.khs.getEmail(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    final User kh = response.body();
                    btn_buyAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (kh.getTenKH() == "" || kh.getPhone() == 0 || kh.getDiaChi() == "") {
                                showDialodTemp();
                            } else {
                                Gson gson = new Gson();
                                String jsonSP = gson.toJson(arrSP);
                                Intent intent = new Intent(ChiTietGioHangActivity.this, ChiTietDonHangActivity.class);
                                intent.putExtra("LISTSP", jsonSP);
                                startActivity(intent);
                                Toast.makeText(ChiTietGioHangActivity.this, "Mua hang thanh cong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void showDialodTemp() {
        final TextView txt_username, txt_mail, txt_phone, txt_position, txt_mail_no;
        final EditText edt_username, edt_phone, edt_position;
        final Button btn_fix, btn_save;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ChiTietGioHangActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_thongtin_temp, null);
        txt_username = mView.findViewById(R.id.txt_username);
        txt_mail = mView.findViewById(R.id.txt_mail);
        txt_phone = mView.findViewById(R.id.txt_phone);
        txt_position = mView.findViewById(R.id.txt_position);

        edt_username = mView.findViewById(R.id.edt_username);
        txt_mail_no = mView.findViewById(R.id.txt_mail_no);
        edt_phone = mView.findViewById(R.id.edt_phone);
        edt_position = mView.findViewById(R.id.edt_position);

        btn_fix = mView.findViewById(R.id.btn_fix);
        btn_save = mView.findViewById(R.id.btn_save);

        ApiService.getInstance().getUserTheoEmail(MainActivity.khs.getEmail(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User kh = response.body();
                    txt_username.setText(kh.getTenKH());
                    txt_phone.setText(kh.getPhone() + "");
                    txt_position.setText(kh.getDiaChi());
                    txt_mail.setText(kh.getEmail());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        btn_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_username.setVisibility(View.GONE);
                edt_username.setVisibility(View.VISIBLE);
                edt_username.setText(txt_username.getText().toString());


                txt_mail_no.setText(txt_mail.getText().toString());
                txt_mail.setVisibility(View.GONE);
                txt_mail_no.setVisibility(View.VISIBLE);


                txt_phone.setVisibility(View.GONE);
                edt_phone.setVisibility(View.VISIBLE);
                edt_phone.setText(txt_phone.getText().toString());


                txt_position.setVisibility(View.GONE);
                edt_position.setVisibility(View.VISIBLE);
                edt_position.setText(txt_position.getText().toString());


                btn_fix.setVisibility(View.GONE);
                btn_save.setVisibility(View.VISIBLE);

                Toast.makeText(ChiTietGioHangActivity.this, "Thêm thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_username.getText().toString();
                String phone = edt_phone.getText().toString();
                String diachi = edt_position.getText().toString();

                txt_mail.setVisibility(View.VISIBLE);
                txt_username.setVisibility(View.VISIBLE);
                txt_position.setVisibility(View.VISIBLE);
                txt_phone.setVisibility(View.VISIBLE);

                txt_mail_no.setVisibility(View.GONE);
                edt_username.setVisibility(View.GONE);
                edt_phone.setVisibility(View.GONE);
                edt_position.setVisibility(View.GONE);

                txt_username.setText(ten);
                txt_phone.setText(phone);
                txt_position.setText(diachi);

                btn_fix.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.GONE);

                ApiService.getInstance().getFixAccount(MainActivity.khs.getMaKH(), txt_username.getText().toString(),
                        MainActivity.khs.getEmail(), Integer.parseInt(txt_phone.getText().toString()),
                        txt_position.getText().toString(), MainActivity.khs.getPassword(),
                        new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });

                Toast.makeText(ChiTietGioHangActivity.this, "Bạn đã thêm thông tin thành công", Toast.LENGTH_SHORT).show();
            }
        });

        dialogBuilder.setView(mView);
        dialogBuilder.setTitle("Thêm thông tin khách hàng");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

    }

    private void addControls() {
        arrSpFirebase = new ArrayList<>();
        arrSP = new ArrayList<>();
        buttonClickListener = new ButtonClickListener() {
            @Override
            public void onBtnClick(int position) {
                FirebaseDatabase.getInstance().getReference().child("GioHang").child(MainActivity.khs.getMaKH() + "").child(arrSP.get(position).getMaSP() + "").removeValue();
                Toast.makeText(ChiTietGioHangActivity.this, arrSP.get(position).getTenSP(), Toast.LENGTH_SHORT).show();
                arrSP.remove(position);
                gioHangAdapter.notifyItemRemoved(position);
            }
        };

        rcl_giohang = findViewById(R.id.rcl_giohang);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(ChiTietGioHangActivity.this);
        rcl_giohang.setLayoutManager(manager);
        rcl_giohang.addItemDecoration(new DividerItemDecoration(ChiTietGioHangActivity.this, RecyclerView.VERTICAL));
        rcl_giohang.setItemAnimator(new DefaultItemAnimator());
        gioHangAdapter = new GioHangAdapter(ChiTietGioHangActivity.this, arrSP, buttonClickListener);
        rcl_giohang.setAdapter(gioHangAdapter);

        img_back = findViewById(R.id.img_back);
        txt_giaAll = findViewById(R.id.txt_giaAll);
        btn_buyAll = findViewById(R.id.btn_buyAll);


        FirebaseDatabase.getInstance().getReference().child("GioHang").child(MainActivity.khs.getMaKH() + "").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //arrSpFirebase.add(dataSnapshot.getValue(SanPhamFirebase.class));
                SanPhamFirebase sanPhamFirebase = dataSnapshot.getValue(SanPhamFirebase.class);

                arrSP.add(new SanPham(sanPhamFirebase.getMaSP(), sanPhamFirebase.getTenSP(), sanPhamFirebase.getGiaSP(), sanPhamFirebase.getHinhSP(), sanPhamFirebase.getSlSP()));
                gioHangAdapter.notifyDataSetChanged();
                int Tong = 0;
                for (SanPham sp : arrSP) {
                    Tong = Tong + sp.getSlSP() * sp.getDonGia();
                }

                NumberFormat numberFormat = new DecimalFormat("###,###");
                txt_giaAll.setText(numberFormat.format(Tong) + " VNĐ");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
