package com.example.uimihnathome.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uimihnathome.R;

public class DanhMucFragment extends Fragment {
    View view;

    View vi_phone, vi_tv, vi_tablet, vi_laptop, vi_watch, vi_headphone;

    ImageView img_search;
    EditText edt_search;

    TextView txt_phoneDM, txt_tvDM, txt_tabletDM, txt_laptopDM, txt_watchDM,txt_speakerDM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danhmuc, container, false);
        showListPhone();

        addControls();
        addEvents();

        return view;
    }

    private void addEvents() {
        img_search.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {
                visible = !visible;
                edt_search.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });

        txt_phoneDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_phone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.maker_footer_red));
                vi_tv.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tablet.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_laptop.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_watch.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_headphone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));

                showListPhone();
            }
        });

        txt_tvDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_phone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tv.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.maker_footer_red));
                vi_tablet.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_laptop.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_watch.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_headphone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));

                showListTV();
            }
        });

        txt_tabletDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_phone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tv.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tablet.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.maker_footer_red));
                vi_laptop.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_watch.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_headphone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));

                showListTablet();
            }
        });

        txt_laptopDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_phone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tv.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tablet.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_laptop.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.maker_footer_red));
                vi_watch.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_headphone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));

                showListLaptop();
            }
        });

        txt_watchDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_phone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tv.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tablet.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_laptop.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_watch.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.maker_footer_red));
                vi_headphone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));

                showListWatch();
            }
        });

        txt_speakerDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_phone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tv.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_tablet.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_laptop.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_watch.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
                vi_headphone.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.maker_footer_red));

                showListSpeaker();
            }
        });

    }

    private void showListPhone() {
//        Toast.makeText(getContext(), "List Dien Thoai", Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction().replace(R.id.containerLoaiSP,new DienThoaiFragment()).commit();
    }

    private void showListTV() {
//        Toast.makeText(getContext(), "ListTV", Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction().replace(R.id.containerLoaiSP, new TVFragment()).commit();
    }

    private void showListLaptop(){
        getFragmentManager().beginTransaction().replace(R.id.containerLoaiSP, new LaptopFragment()).commit();
    }

    private void showListTablet(){
        getFragmentManager().beginTransaction().replace(R.id.containerLoaiSP, new TabletFragment()).commit();
    }

    private void showListWatch(){
        getFragmentManager().beginTransaction().replace(R.id.containerLoaiSP, new WatchFragment()).commit();
    }

    private void showListSpeaker(){
        getFragmentManager().beginTransaction().replace(R.id.containerLoaiSP, new SpeakerFragment()).commit();
    }

    private void addControls() {
        img_search = view.findViewById(R.id.img_search);
        edt_search = view.findViewById(R.id.edt_search);

        txt_phoneDM = view.findViewById(R.id.txt_phoneDM);
        txt_tvDM = view.findViewById(R.id.txt_tvDM);
        txt_tabletDM = view.findViewById(R.id.txt_tabletDM);
        txt_laptopDM = view.findViewById(R.id.txt_laptopDM);
        txt_watchDM = view.findViewById(R.id.txt_watchDM);
        txt_speakerDM = view.findViewById(R.id.txt_speakerDM);

        vi_phone = view.findViewById(R.id.vi_phone);
        vi_tv = view.findViewById(R.id.vi_tv);
        vi_tablet = view.findViewById(R.id.vi_tablet);
        vi_laptop = view.findViewById(R.id.vi_laptop);
        vi_watch = view.findViewById(R.id.vi_watch);
        vi_headphone = view.findViewById(R.id.vi_headphone);

    }

}
