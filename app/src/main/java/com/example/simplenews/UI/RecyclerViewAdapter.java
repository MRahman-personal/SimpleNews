package com.example.simplenews.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.simplenews.Api.Article;
import com.example.simplenews.Api.StoryResponse;
import com.example.simplenews.R;
import com.example.simplenews.Webview.OnlineNewsWebView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder> {

    private StoryResponse storyResponse;
    private final Context context;

    public RecyclerViewAdapter(Context context, StoryResponse storyResponse) {
        this.context = context;
        this.storyResponse = storyResponse;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        final Viewholder holder = new Viewholder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article model = storyResponse.getArticles().get(holder.getAdapterPosition());
                Intent intent = new Intent(context, OnlineNewsWebView.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("description", model.getDescription());
                intent.putExtra("url", model.getUrl());
                intent.putExtra("image_url", model.getUrlToImage());
                intent.putExtra("published", model.getPublishedAt());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.Viewholder holder, int position) {
        Article model = storyResponse.getArticles().get(position);
        holder.title.setText(model.getTitle());
        holder.summary.setText(model.getDescription());
        holder.postedAt.setText(model.getPublishedAt());
        Glide.with(context).load(model.getUrlToImage()).centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return storyResponse.getArticles().size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, summary, postedAt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            summary = itemView.findViewById(R.id.summary);
            postedAt = itemView.findViewById(R.id.postedAt);
            image = itemView.findViewById(R.id.image);
        }
    }

    public void setItems(StoryResponse response){
        storyResponse = response;
        notifyDataSetChanged();
    }

}