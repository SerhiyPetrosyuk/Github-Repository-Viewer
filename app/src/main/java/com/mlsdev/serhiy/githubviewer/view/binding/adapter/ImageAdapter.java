package com.mlsdev.serhiy.githubviewer.view.binding.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageAdapter {
    @BindingAdapter({"bind:imageUrl", "bind:errorImage"})
    public static void displayImage(final ImageView imageView, String imageUrl, Drawable errorImageHolder) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .error(errorImageHolder)
                .centerCrop()
                .into(imageView);
    }
}
