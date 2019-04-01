package com.cameragallery;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>  {

private ArrayList<Uri> images;


    ImageAdapter(ArrayList<Uri> images)
    {
      this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_layout, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {
        Uri image_id = images.get(i);
//       Bitmap bitmap = MediaStore.Images.Media.getBitmap(this, image_id);
        viewHolder.imageAlbum.setImageURI(image_id);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    static class ImageViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageAlbum;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAlbum = itemView.findViewById(R.id.album);
        }
    }
}
