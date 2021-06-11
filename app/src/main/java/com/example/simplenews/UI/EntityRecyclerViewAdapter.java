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
import com.example.simplenews.Database.ArticleEntity;
import com.example.simplenews.R;
import com.example.simplenews.Webview.SavedNewsWebView;
import java.util.List;

public class EntityRecyclerViewAdapter extends RecyclerView.Adapter<EntityRecyclerViewAdapter.Viewholder> {

    private List<ArticleEntity> articles;
    private final Context context;

    public EntityRecyclerViewAdapter(Context context, List<ArticleEntity> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public EntityRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);

        final Viewholder holder = new Viewholder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleEntity entity = articles.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, SavedNewsWebView.class);
                intent.putExtra("entity_url", entity.getUrl());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntityRecyclerViewAdapter.Viewholder holder, int position) {
        ArticleEntity entity = articles.get(position);
        holder.title.setText(entity.getTitle());
        holder.summary.setText(entity.getDescription());
        holder.postedAt.setText(entity.getPublishedAt());
        Glide.with(context).load(entity.getUrlToImage()).centerCrop().into(holder.image);

    }

    @Override
    public int getItemCount() {
        return articles.size();
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

    public void setItems(List<ArticleEntity> articlesList){
        articles = articlesList;
        notifyDataSetChanged();
    }

}
