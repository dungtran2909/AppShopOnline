package com.example.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.impl.ButtonClickListener;
import com.example.impl.CheckClickListener;
import com.example.impl.ItemClickListener;
import com.example.model.DonHang;
import com.example.model.SanPham;
import com.example.uimihnathome.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.uimihnathome.R.color.color_white;
import static com.example.uimihnathome.R.color.grey_enable_false;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ItemHolder> {
    Context context;
    ArrayList<DonHang> arrDH;
    ItemClickListener itemClickListener;

    DonHang dh = new DonHang();

    public DonHangAdapter(Context context, ArrayList<DonHang> arrDH, ItemClickListener itemClickListener) {
        this.context = context;
        this.arrDH = arrDH;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_donhang, null);
        ItemHolder itemHolder = new ItemHolder(view);


        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        final DonHang donHang = arrDH.get(position);

        holder.txt_maDH.setText(donHang.getMaDonHang() + "");
        String[] ngayDH = donHang.getNgayDat().split("T");
        holder.txt_ngayDH.setText(ngayDH[0]);
        String[] ngayGH = donHang.getNgayGiao().split("T");
        holder.txt_ngayGH.setText(ngayGH[0]);

        if (donHang.getTrangThai() == 0) {
            holder.txt_trangthai.setTextColor(Color.RED);
            holder.txt_trangthai.setText("Đang chờ xử lý");
            holder.img_trangthai.setImageResource(R.drawable.icon_loading);
        } else {
            holder.txt_trangthai.setTextColor(Color.GREEN);
            holder.txt_trangthai.setText("Đã giao hàng");
            holder.img_trangthai.setImageResource(R.drawable.icon_loaded);
        }

//        if (donHang.getTrangThai() == 0) {
//            holder.txt_trangthai.setTextColor(R.color.red);
//            holder.txt_trangthai.setText("Đã giao hàng");
//            holder.img_trangthai.setImageResource(R.drawable.icon_loaded);
//        } else {
//            holder.txt_trangthai.setTextColor(R.color.green_chat_right);
//            holder.txt_trangthai.setText("Đang chờ xử lý");
//            holder.img_trangthai.setImageResource(R.drawable.icon_loading);
//        }
    }

    @Override
    public int getItemCount() {
        return arrDH.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView img_trangthai;
        public TextView txt_maDH, txt_ngayDH, txt_ngayGH, txt_trangthai;

        public ItemHolder(@NonNull final View itemView) {
            super(itemView);
            img_trangthai = itemView.findViewById(R.id.img_trangthai);
            txt_maDH = itemView.findViewById(R.id.txt_maDH);
            txt_ngayDH = itemView.findViewById(R.id.txt_ngayDH);
            txt_ngayGH = itemView.findViewById(R.id.txt_ngayGH);
            txt_trangthai = itemView.findViewById(R.id.txt_trangthai);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
