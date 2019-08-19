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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adapter.DonHangAdapter;
import com.example.adapter.SanPhamAdapter;
import com.example.impl.ItemClickListener;
import com.example.model.DonHang;
import com.example.network.ApiService;
import com.example.uimihnathome.ChiTietGioHangActivity;
import com.example.uimihnathome.MainActivity;
import com.example.uimihnathome.R;
import com.example.uimihnathome.ThongTinSanPhamActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongBaoFragment extends Fragment {
    View view;

    RecyclerView rcl_donhang;
    ArrayList<DonHang> arrDH;
    DonHangAdapter donHangAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thongbao,container,false);

        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        getAllDonHang();
    }

    private void addControls() {

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(int position) {
            }
        };

        rcl_donhang = view.findViewById(R.id.rcl_donhang);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcl_donhang.setLayoutManager(manager);
//        rcl_dienthoai.setHasFixedSize(true);
        rcl_donhang.addItemDecoration(new DividerItemDecoration(view.getContext(),RecyclerView.VERTICAL));
        rcl_donhang.setItemAnimator(new DefaultItemAnimator());
        arrDH = new ArrayList<>();
        donHangAdapter = new DonHangAdapter(getContext(), arrDH, itemClickListener);
        rcl_donhang.setAdapter(donHangAdapter);

    }

    private void getAllDonHang(){
        ApiService.getInstance().getListDonHangTheoDanhMuc(MainActivity.khs.getMaKH(), new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                if(response.isSuccessful()){
                    ArrayList<DonHang> donHangArrayList = (ArrayList<DonHang>) response.body();
                    arrDH.clear();
                    arrDH.addAll(donHangArrayList);
                    donHangAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {

            }
        });
    }
}
