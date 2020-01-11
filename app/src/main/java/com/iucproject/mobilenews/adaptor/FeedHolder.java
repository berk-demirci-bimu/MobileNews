package com.iucproject.mobilenews.adaptor;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.iucproject.mobilenews.R;

public class FeedHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public LinearLayout layo;
    public TextView content;
    public CardView cardView;
    public ImageView img;
    public FeedHolder(@NonNull View itemView) {
        super(itemView);
        title =  itemView.findViewById(R.id.article_title);
        layo = itemView.findViewById(R.id.layo);
        img = itemView.findViewById(R.id.article_image);
        content = itemView.findViewById(R.id.article_content);
        cardView = itemView.findViewById(R.id.card_view);
    }

}
