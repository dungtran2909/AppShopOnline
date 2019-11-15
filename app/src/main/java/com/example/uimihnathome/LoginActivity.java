package com.example.uimihnathome;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity {
    EditText edt_dnTK, edt_dnMK;
    Button btn_DN, btn_DK;

    ProgressDialog progressDialog;
    AlertDialog.Builder alertDialog;
    CheckBox chkRemember;
    SharedPreferences sharedPreferences;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String EMAIL = "emailKey";
    public static final String PASS = "passKey";
    public static final String REMEMBER = "remember";

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        addControls();
        addEvents();
        loadData();

    }

    private void addEvents() {
        btn_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btn_DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chkRemember.isChecked())
                    //lưu lại thông tin đăng nhập
                    saveData(edt_dnTK.getText().toString(), edt_dnMK.getText().toString());
                else
                    clearData();
                checkEmail(edt_dnTK.getText().toString());
            }
        });


    }

    private void checkEmail(String email) {
        ApiService.getInstance().getUserTheoEmail(email, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User kh = response.body();
                    if (kh != null) {
                        if (kh.getPassword().equals(edt_dnMK.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("KHACHHANG", kh);
                            startActivity(intent);
                        } else
                            Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void addControls() {

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        edt_dnMK = findViewById(R.id.edt_dnMK);
        edt_dnTK = findViewById(R.id.edt_dnTK);
        btn_DN = findViewById(R.id.btn_DN);
        btn_DK = findViewById(R.id.btn_DK);
        chkRemember = findViewById(R.id.chkRemember);
    }

    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void saveData(String email, String Pass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        editor.putString(PASS, Pass);
        editor.putBoolean(REMEMBER, chkRemember.isChecked());
        editor.commit();
    }

    private void loadData() {
        if (sharedPreferences.getBoolean(REMEMBER, false)) {
            edt_dnTK.setText(sharedPreferences.getString(EMAIL, ""));
            edt_dnMK.setText(sharedPreferences.getString(PASS, ""));
            chkRemember.setChecked(true);
        } else
            chkRemember.setChecked(false);
    }
}
