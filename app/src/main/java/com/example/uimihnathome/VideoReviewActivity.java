package com.example.uimihnathome;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.VideoReviewAdapter;
import com.example.model.SanPham;
import com.example.model.VideoReview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VideoReviewActivity extends AppCompatActivity {
    public static String API_KEY = "AIzaSyD9PrqM5MxSa-CKW2KGhnmNbeMG07ZJem0";

    SanPham sanPham = new SanPham();

    //    String tenSP = ThongTinSanPhamActivity.sp.getTenSP();
    ListView lvVideo;
    VideoReviewAdapter adapterVideo;
    ArrayList<VideoReview> arrVideo;
//    String ulrGetJson = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q="+tenSP+"karaoke"+"&key=" + API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_review);

        Intent intentSP = getIntent();
        sanPham = (SanPham) intentSP.getSerializableExtra("SPTruyen");


        String tenSP = sanPham.getTenSP();

        String ulrGetJson = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=" + tenSP + "reviews" + "&key=" + API_KEY;


        addControls();
        addEvents();
        GetJsonYouTube(ulrGetJson);
    }

    private void addEvents() {
        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(VideoReviewActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideoSing", arrVideo.get(i).getIdVideo());
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvVideo = findViewById(R.id.lvVideo);
        arrVideo = new ArrayList<VideoReview>(); //???? arrVideo = empty
        adapterVideo = new VideoReviewAdapter(VideoReviewActivity.this, R.layout.item_video_review, arrVideo);
        lvVideo.setAdapter(adapterVideo);
    }

    private void GetJsonYouTube(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(VideoReviewActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonItems = response.getJSONArray("items");

                    String title = "";
                    String url = "";
                    String idVideo = "";


                    for (int i = 0; i < jsonItems.length(); i++) {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);

                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");
                        JSONObject jsonId = jsonItem.getJSONObject("id");
                        idVideo = jsonId.getString("videoId");

                        arrVideo.add(new VideoReview(title, url, idVideo));

                    }

                    adapterVideo.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Loi", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
