package com.example.uimihnathome.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.SanPhamAdapter;
import com.example.impl.ItemClickListener;
import com.example.model.SanPham;
import com.example.network.ApiService;
import com.example.uimihnathome.R;
import com.example.uimihnathome.ThongTinSanPhamActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeakerFragment extends Fragment {
    View view;

    RecyclerView rcl_speaker;
    ArrayList<SanPham> arrSP;
    SanPhamAdapter sanPhamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_speaker, container, false);
        addControls();
        addEvents();
        //loaddata();

        return view;
    }

    private void addEvents() {
        loaddata();
    }

    private void addControls() {
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), ThongTinSanPhamActivity.class);
                intent.putExtra("SANPHAM", arrSP.get(position));
                startActivity(intent);
            }
        };

        rcl_speaker = view.findViewById(R.id.rcl_speaker);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcl_speaker.setLayoutManager(manager);
//        rcl_speaker.setHasFixedSize(true);
        rcl_speaker.addItemDecoration(new DividerItemDecoration(view.getContext(),RecyclerView.VERTICAL));
        rcl_speaker.setItemAnimator(new DefaultItemAnimator());
        arrSP = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getContext(), arrSP, itemClickListener);
        rcl_speaker.setAdapter(sanPhamAdapter);
    }



    void loaddata() {
        ApiService.getInstance().getListSanPham(6,new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                Log.d("abcv","OK: "+response.body().size());
                arrSP.removeAll(arrSP);
                arrSP.addAll (response.body());
                sanPhamAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Log.d("abcv","OK: "+t.getMessage());

            }
        });

    }
}
