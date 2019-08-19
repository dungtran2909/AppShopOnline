package com.example.uimihnathome.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.impl.ButtonClickListener;
import com.example.impl.ItemClickListener;
import com.example.model.SanPham;
import com.example.uimihnathome.ChiTietGioHangActivity;
import com.example.uimihnathome.R;
import com.example.uimihnathome.ThongTinSanPhamActivity;
import com.google.android.youtube.player.internal.v;

import static com.example.uimihnathome.R.color.grey_enable_false;

@SuppressLint("ValidFragment")
public class DialogBottomSheetBuy extends BottomSheetDialogFragment {
    View view;
    Button btn_buyitem;

    ImageView img_subtract, img_plus;
    TextView txt_number, txt_giaSP, txt_tonSP;
    ImageView img_hinhSP;

    ButtonClickListener buttonClickListener;

    SanPham sanPham;

    @SuppressLint("ValidFragment")
    public DialogBottomSheetBuy(ButtonClickListener buttonClickListener, SanPham sanPham) {
        this.buttonClickListener = buttonClickListener;
        this.sanPham = sanPham;
    }

    SanPham sp = new SanPham();

    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_bottomsheet_buy, container, false);

        addControls();
        addEvents();

        return view;
    }

    private void addEvents() {
        btn_buyitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                buttonClickListener.Click();
                Intent intent = new Intent(getActivity(), ChiTietGioHangActivity.class);
                sanPham.setSlSP(Integer.parseInt(txt_number.getText().toString()));
                if (sanPham.getSlSP() == 0) {
                    Toast.makeText(getContext(), "Hãy chọn số lượng mua hàng", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("SANPHAM", sanPham);
                    startActivity(intent);
                }
            }
        });

        img_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getSlSP() > 0) {
                    img_subtract.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_white));
                    sp.setSlSP(Integer.parseInt(txt_number.getText().toString()) - 1);
                } else {
                    img_subtract.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey_enable_false));
                }
                txt_number.setText(sp.getSlSP() + "");
            }
        });

        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getSlSP() < 100) {
                    img_subtract.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_white));
                    sp.setSlSP(Integer.parseInt(txt_number.getText().toString()) + 1);
                } else {
                    img_plus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey_enable_false));
                }
                txt_number.setText(sp.getSlSP() + "");
            }
        });
    }

    private void addControls() {
        btn_buyitem = view.findViewById(R.id.btn_buyitem);

        img_subtract = view.findViewById(R.id.img_subtract);
        img_plus = view.findViewById(R.id.img_plus);
        txt_number = view.findViewById(R.id.txt_number);

        txt_giaSP = view.findViewById(R.id.txt_giaSP);
        txt_tonSP = view.findViewById(R.id.txt_tonSL);
        img_hinhSP = view.findViewById(R.id.img_hinhSP);

        txt_giaSP.setText(sp.getDonGia());
        txt_tonSP.setText(sp.getSoLuongTon());

    }

//    public interface BottomSheetListener {
//        void onButtonlicked(String text);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try{
//
//        } catch (ClassCastException e){
//            throw new ClassCastException(context.toString()+"aaaaaaaa");
//        }
//    }
}
