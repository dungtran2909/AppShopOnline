package com.example.uimihnathome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.User;
import com.example.network.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText edt_dkyTK, edt_dkyMK, edt_redkyMK;
    Button btn_DK;
    TextView txt_reDN;

    String tenKh = "", diachi = "", email, password;
    int phone = 0;

    ArrayList<User> arrUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        getAllList();

        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test;
                int kt = 0;
                for (User user : arrUser) {
                    test = user.getEmail().toString();
                    if (test.equals(edt_dkyTK.getText().toString())) {
                        kt++;
                    }
                }

                if (edt_dkyTK.getText().toString().equals("")) {
                    if (edt_dkyMK.getText().toString().equals("")) {
                        Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ email và mật khẩu đăng ký", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Vui lòng nhập email đăng ký", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (edt_dkyMK.getText().toString().equals("")) {
                        Toast.makeText(SignUpActivity.this, "Vui lòng nhập mật khẩu đăng ký", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!edt_dkyMK.getText().toString().equals(edt_redkyMK.getText().toString())) {
                            Toast.makeText(SignUpActivity.this, "Vui lòng nhập đúng mật khẩu xác nhận", Toast.LENGTH_SHORT).show();
                        } else {
                            if (kt == 0) {
                                XuLyDK();
                                Toast.makeText(SignUpActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                xyLyShowDialog();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Tài khoản đã đăng ký", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }


//                if(kt==0){
//                    XuLyDK();
//                    Toast.makeText(SignUpActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(SignUpActivity.this, "Tai khoan da dang ky", Toast.LENGTH_SHORT).show();
//                }


            }
        });

        txt_reDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xyLyShowDialog() {
        Button btn_diareDN;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUpActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_dangky, null);
        btn_diareDN = mView.findViewById(R.id.btn_diareDN);

        btn_diareDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        dialogBuilder.setView(mView);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void XuLyDK() {
        ApiService.getInstance().CreateAcc(tenKh, edt_dkyTK.getText().toString(), phone, diachi, edt_dkyMK.getText().toString(),
                new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                    }
                });
    }

    private void getAllList() {
        ApiService.getInstance().getListUser(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    ArrayList<User> list = (ArrayList<User>) response.body();
                    arrUser.addAll(list);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void addControls() {
        edt_dkyTK = findViewById(R.id.edt_dkyTK);
        edt_dkyMK = findViewById(R.id.edt_dkyMK);
        edt_redkyMK = findViewById(R.id.edt_redkyMK);
        txt_reDN = findViewById(R.id.txt_reDN);
        btn_DK = findViewById(R.id.btnDK);
    }

}
