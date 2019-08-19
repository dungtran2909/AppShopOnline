package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.impl.ButtonClickListener;
import com.example.impl.ItemClickListener;
import com.example.model.SanPham;
import com.example.uimihnathome.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CTDonHangAdapter extends RecyclerView.Adapter<CTDonHangAdapter.ItemHolder> {
    Context context;
    ArrayList<SanPham> arrSP;
    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CTDonHangAdapter(Context context, ArrayList<SanPham> arrSP, ItemClickListener itemClickListener) {
        this.context = context;
        this.arrSP = arrSP;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ctdonhang, null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        SanPham sanPham = arrSP.get(position);

        Picasso.with(context).load(sanPham.getHinhSP()).into(holder.img_hinhSP);
        holder.txt_tenSP.setText(sanPham.getTenSP());
        holder.txt_slSP.setText("x"+sanPham.getSlSP()+"");

        NumberFormat numberFormat = new DecimalFormat("###,###");
        holder.txt_tongDG.setText(numberFormat.format(sanPham.getDonGia()*sanPham.getSlSP())+" VNĐ");
    }

    @Override
    public int getItemCount() {
        return arrSP.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView img_hinhSP;
        public TextView txt_tenSP,txt_slSP, txt_tongDG;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            img_hinhSP = itemView.findViewById(R.id.img_hinhSP);
            txt_tenSP = itemView.findViewById(R.id.txt_tenSP);
            txt_slSP = itemView.findViewById(R.id.txt_slSP);
            txt_tongDG = itemView.findViewById(R.id.txt_tongDG);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });


        }
    }
}
