package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.impl.ItemClickListener;
import com.example.model.SanPham;
import com.example.uimihnathome.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    Context context;
    ArrayList<SanPham> arrSP;
    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrSP, ItemClickListener itemClickListener) {
        this.context = context;
        this.arrSP = arrSP;
        this.itemClickListener = itemClickListener;
    }

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrSP) {
        this.context = context;
        this.arrSP = arrSP;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sanpham, null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        SanPham sanPham = arrSP.get(position);

        Picasso.with(context).load(sanPham.getHinhSP()).into(holder.img_hinhSP);
//        holder.img_hinhSP.setImageResource(sanPham.getHinhSP());
        holder.txt_tenSP.setText(sanPham.getTenSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        holder.txt_giaSP.setText(numberFormat.format(sanPham.getDonGia())+" VNĐ");

        String[] thuGon = sanPham.getMoTa().split(" ");
        if (thuGon.length>3){
            holder.txt_motaSP.setText(thuGon[0]+" "+thuGon[1]+ " "+thuGon[2]);
        }
        else
            holder.txt_motaSP.setText(thuGon[0]+" "+thuGon[1]);
    }

    @Override
    public int getItemCount() {
        return arrSP.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView img_hinhSP;
        public TextView txt_tenSP,txt_giaSP, txt_motaSP;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            img_hinhSP = itemView.findViewById(R.id.img_hinhSP);
            txt_tenSP = itemView.findViewById(R.id.txt_tenSP);
            txt_giaSP = itemView.findViewById(R.id.txt_giaSP);
            txt_motaSP = itemView.findViewById(R.id.txt_motaSP);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
