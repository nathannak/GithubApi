package com.alin.githubapi.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alin.githubapi.R;
import com.alin.githubapi.models.Item;
import com.alin.githubapi.presenter.activities.RepoDetailActivity;
import com.alin.githubapi.view.MyRepoViewHolder;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyRepoAdapter extends RecyclerView.Adapter<MyRepoViewHolder> {
    Context context;
    List<Item> list;

    public MyRepoAdapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyRepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false);
        return new MyRepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyRepoViewHolder holder, final int position) {

        holder.update(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item item = list.get(position);
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra("repoUrl", item.getHtmlUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

