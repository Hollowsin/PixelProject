package com.example.trainee.pixelproject;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ImageCrop>{
    ArrayList<Bitmap> chunkedImages;

    RVAdapter(ArrayList<Bitmap> chunkedImages){
        this.chunkedImages = chunkedImages;
    }

    @NonNull
    @Override
    public ImageCrop onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_image, viewGroup, false);
        return new ImageCrop(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCrop imageCrop, int i) {
       imageCrop.cropArt.setImageBitmap(chunkedImages.get(i));

    }

    @Override
    public int getItemCount() {
        return chunkedImages.size();
    }

    static class ImageCrop extends RecyclerView.ViewHolder {
        ImageView cropArt;
        ImageCrop(View itemView) {
            super(itemView);
            cropArt = itemView.findViewById(R.id.imageView);

        }
    }

}