package com.iucproject.mobilenews.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iucproject.mobilenews.R;
import com.iucproject.mobilenews.Webview;
import com.iucproject.mobilenews.articles.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<FeedHolder> {

    private List<Article> article;
    private Context context;

    public Adapter(List<Article> article,Context context) {
        this.article = article;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles,parent,false);
        return new FeedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHolder holder, int position) {
    //final FeedHolder feedHolder = holder;
    final Article art = article.get(position);
    holder.title.setText(art.getTitle());
    holder.content.setText(art.getDescription());
    //holder.date.setText(art.getPublishedAt());
    String imgUrl = art.getUrlToImage();
    Picasso.with(context).load(imgUrl).into(holder.img);
    holder.layo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Webview.class);
            intent.putExtra("url",art.getUrl());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return article.size();
    }


}
