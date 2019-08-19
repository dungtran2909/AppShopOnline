package com.example.uimihnathome;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.User;
import com.example.network.ApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietThongTinActivity extends AppCompatActivity {
    ImageView iv_backTaiKhoan, img_fix;

    TextView txt_mail, txt_phone, txt_position, txt_tenUser;
    EditText edt_mail, edt_phone, edt_position, edt_tenUser;

    EditText edt_password_old, edt_password_new, edt_password_confirm;

    Button btn_save, btn_change_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thong_tin);


        addControls();
        addEvents();
    }


    private void addEvents() {
        iv_backTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_mail.setVisibility(View.VISIBLE);
                txt_phone.setVisibility(View.GONE);
                txt_position.setVisibility(View.GONE);
                txt_tenUser.setVisibility(View.GONE);

//                edt_mail.setVisibility(View.VISIBLE);
                edt_phone.setVisibility(View.VISIBLE);
                edt_position.setVisibility(View.VISIBLE);
                edt_tenUser.setVisibility(View.VISIBLE);

                Toast.makeText(ChiTietThongTinActivity.this, "Bạn có thể chỉnh sửa thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String mail = edt_mail.getText().toString();
                String phone = edt_phone.getText().toString();
                String position = edt_position.getText().toString();
                String ten = edt_tenUser.getText().toString();

                txt_mail.setVisibility(View.VISIBLE);
                txt_phone.setVisibility(View.VISIBLE);
                txt_position.setVisibility(View.VISIBLE);
                txt_tenUser.setVisibility(View.VISIBLE);

//                edt_mail.setVisibility(View.GONE);
                edt_phone.setVisibility(View.GONE);
                edt_position.setVisibility(View.GONE);
                edt_tenUser.setVisibility(View.GONE);

//                txt_mail.setText(mail);
                txt_phone.setText(phone);
                txt_position.setText(position);
                txt_tenUser.setText(ten);

                fixInfo();

                Toast.makeText(ChiTietThongTinActivity.this, "Bạn đã lưu thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.khs.getPassword().equals(edt_password_old.getText().toString())) {
                    if (edt_password_new.getText().toString().equals(edt_password_confirm.getText().toString())) {
                        changePass();
                        Toast.makeText(ChiTietThongTinActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(ChiTietThongTinActivity.this, "Mật khẩu xác nhận không chính xác", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(ChiTietThongTinActivity.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void fixInfo(){
        ApiService.getInstance().getFixAccount(MainActivity.khs.getMaKH(), txt_tenUser.getText().toString(), MainActivity.khs.getEmail(), Integer.parseInt(txt_phone.getText().toString()), txt_position.getText().toString(), MainActivity.khs.getPassword(), new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    private void changePass(){
        ApiService.getInstance().getChangePassFromEmail(MainActivity.khs.getEmail(), edt_password_new.getText().toString(), new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    private void getInfo(){
        ApiService.getInstance().getUserTheoEmail(MainActivity.khs.getEmail(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User kh = response.body();
                    txt_tenUser.setText(kh.getTenKH());
                    txt_mail.setText(kh.getEmail());
                    txt_phone.setText(kh.getPhone()+"");
                    txt_position.setText(kh.getDiaChi());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void addControls() {
        iv_backTaiKhoan = findViewById(R.id.iv_backTaiKhoan);
        img_fix = findViewById(R.id.img_fix);

        txt_mail = findViewById(R.id.txt_mail);
        txt_phone = findViewById(R.id.txt_phone);
        txt_position = findViewById(R.id.txt_position);
        txt_tenUser = findViewById(R.id.txt_tenUser);

        edt_mail = findViewById(R.id.edt_mail);
        edt_phone = findViewById(R.id.edt_phone);
        edt_position = findViewById(R.id.edt_position);
        edt_tenUser = findViewById(R.id.edt_tenUser);

        edt_password_old = findViewById(R.id.edt_password_old);
        edt_password_new = findViewById(R.id.edt_password_new);
        edt_password_confirm = findViewById(R.id.edt_password_confirm);

        btn_save = findViewById(R.id.btn_save);
        btn_change_pass = findViewById(R.id.btn_change_pass);

        getInfo();
//        txt_tenUser.setText(MainActivity.khs.getTenKH());
//        txt_mail.setText(MainActivity.khs.getEmail());
//        txt_phone.setText(MainActivity.khs.getPhone() + "");
//        txt_position.setText(MainActivity.khs.getDiaChi());


    }
}
