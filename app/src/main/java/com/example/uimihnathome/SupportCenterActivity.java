package com.example.uimihnathome;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class SupportCenterActivity extends AppCompatActivity {
    TabHost tabHost;
    TextView txt_call, txt_send;
    EditText edt_send;
    Button btn_send;
    LinearLayout lnl_send;

    FloatingActionButton fabBack;

    public static String so = "0356484803";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_center);

        addControls();
        addEvents();
    }

    private void addEvents() {
        txt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(so)) {
                    String dial = "tel:" + so;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(SupportCenterActivity.this, "Nhap vao so dien thoai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_send.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {
                visible = !visible;
                lnl_send.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tin = edt_send.getText().toString();
                String send = "smsto:" + so;
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(send));
                intent.putExtra("sms_body", tin);
                startActivity(intent);
            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        fabBack = findViewById(R.id.fabBack);

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        txt_call = findViewById(R.id.txt_call);
        txt_send = findViewById(R.id.txt_send);
        edt_send = findViewById(R.id.edt_send);
        btn_send = findViewById(R.id.btn_send);
        lnl_send = findViewById(R.id.lnl_send);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Trợ Giúp");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("Liên Hệ");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);
    }
}
