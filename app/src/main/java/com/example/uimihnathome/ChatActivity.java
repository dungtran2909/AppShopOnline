package com.example.uimihnathome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.adapter.ChatAdapter;
import com.example.firebase.ChatMessage;

import com.google.android.gms.fido.u2f.api.common.RequestParams;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

import okhttp3.internal.http.HttpHeaders;


public class ChatActivity extends AppCompatActivity {
    FloatingActionButton fabSend;
    EditText edtInput;
    RecyclerView rcyMessege;
    ImageView imgBack;
    ChatAdapter adapter;
    ArrayList<ChatMessage> dsMessages;
    ArrayList<String> dsKEY = new ArrayList<>();
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String urlImage = "https://github.com/quoccuong151197/AppAndroid/blob/master/app/src/main/res/drawable/ic_fist_sub.png";
    private static final String TAG = "ChatActivity";
    String child="ChamSocKhachHangChats";
    String []emails=MainActivity.khs.getEmail().split("@");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addControls();
        addEvents();
    }

    private void addEvents() {
        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aaaaa = urlImage;
                mData.child(child).child(emails[0])
                        .push()
                        .setValue(new ChatMessage(edtInput.getText().toString(), emails[0], urlImage));
                edtInput.setText("");
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        dsMessages = new ArrayList<>();
        fabSend = findViewById(R.id.fab);
        edtInput = findViewById(R.id.edtInput);
        imgBack = findViewById(R.id.iv_back);
        rcyMessege = findViewById(R.id.lvMessages);
        rcyMessege.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        adapter = new ChatAdapter(ChatActivity.this, dsMessages, MainActivity.khs);
        rcyMessege.setAdapter(adapter);
        displayChatMessages();
    }

    private void displayChatMessages() {
        mData.child(child).child(emails[0]).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                dsMessages.add(chatMessage);
                dsKEY.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
                rcyMessege.smoothScrollToPosition(rcyMessege.getAdapter().getItemCount() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

}