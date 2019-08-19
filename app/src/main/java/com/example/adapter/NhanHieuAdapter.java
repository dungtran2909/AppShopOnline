//package com.example.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.impl.ItemClickListener;
//import com.example.model.NhanHieu;
//import com.example.uimihnathome.R;
//
//import java.util.ArrayList;
//
//public class NhanHieuAdapter extends RecyclerView.Adapter<NhanHieuAdapter.ItemHolder> {
//    Context context;
//    ArrayList<NhanHieu> arrBr;
//    ItemClickListener itemClickListener;
//
//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }
//
//    public NhanHieuAdapter(Context context, ArrayList<NhanHieu> arrBr, ItemClickListener itemClickListener) {
//        this.context = context;
//        this.arrBr = arrBr;
//        this.itemClickListener = itemClickListener;
//    }
//
//    public NhanHieuAdapter(Context context, ArrayList<NhanHieu> arrBr) {
//        this.context = context;
//        this.arrBr = arrBr;
//    }
//
//    @NonNull
//    @Override
//    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_thuonghieu, null);
//        ItemHolder itemHolder = new ItemHolder(view);
//
//
//        return itemHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
//        NhanHieu brand = arrBr.get(position);
//        holder.img_logoBrand.setImageResource(brand.getImageBrand());
//        holder.txt_namebrand.setText(brand.getNameBrand());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrBr.size();
//    }
//
//
//    public class ItemHolder extends RecyclerView.ViewHolder {
//        public ImageView img_logoBrand;
//        public TextView txt_namebrand;
//
//        public ItemHolder(@NonNull View itemView) {
//            super(itemView);
//            img_logoBrand = itemView.findViewById(R.id.img_logobrand);
//            txt_namebrand = itemView.findViewById(R.id.txt_namebrand);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemClickListener.onClick(getAdapterPosition());
//                }
//            });
//        }
//    }
//}
