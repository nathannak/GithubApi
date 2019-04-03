package com.alin.githubapi.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.alin.githubapi.R;
import com.alin.githubapi.models.Item;

import androidx.recyclerview.widget.RecyclerView;

public class MyRepoViewHolder extends RecyclerView.ViewHolder
{
    public ImageView photo;
    public TextView name;
    public TextView description;

    public MyRepoViewHolder(View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.imgPhoto);
        name = itemView.findViewById(R.id.tvName);
        description = itemView.findViewById(R.id.tvDescription);
    }

    public void update(Item data) {

        if (data != null) {

            String url = data.getOwner().getAvatarUrl();
            Log.i("url", url);
            Picasso.get().load(url).into(photo);

            name.setText(data.getName());
            description.setText(data.getDescription());
        }
    }
}
