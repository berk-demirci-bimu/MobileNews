package com.iucproject.mobilenews.adaptor;

import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.iucproject.mobilenews.*;
import com.iucproject.mobilenews.roomDB.Datas;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DatasAdapter extends RecyclerView.Adapter<DatasAdapter.DataHolder> {
    private List<Datas> datas = new ArrayList<>();
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.articles,parent,false);
        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        Datas currentData = datas.get(position);
        holder.title.setText(currentData.getTitle());
        holder.desc.setText(currentData.getContent());
        holder.img.setImageResource(R.drawable.no_picture);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<Datas> datas) {
        this.datas = datas;
        notifyDataSetChanged();

    }
    class DataHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private ImageView img;

        public DataHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            desc = itemView.findViewById(R.id.article_content);
            img = itemView.findViewById(R.id.article_image);
        }
    }
}
