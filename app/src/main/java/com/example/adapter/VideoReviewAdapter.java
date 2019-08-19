package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.VideoReview;
import com.example.uimihnathome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoReviewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VideoReview> arrVideo;

    public VideoReviewAdapter(Context context, int layout, List<VideoReview> arrVideo) {
        this.context = context;
        this.layout = layout;
        this.arrVideo = arrVideo;
    }

    @Override
    public int getCount() {
        return arrVideo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgThumbnail;
        TextView txtTitle;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            holder.imgThumbnail = (ImageView) view.findViewById(R.id.imgThumbnail);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        //try {
            VideoReview videoReview = arrVideo.get(i);

           // if (holder.txtTitle != null)
                holder.txtTitle.setText(videoReview.getTitle());

            Picasso.with(context).load(videoReview.getThumbnail()).into(holder.imgThumbnail);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return view;
    }
}
