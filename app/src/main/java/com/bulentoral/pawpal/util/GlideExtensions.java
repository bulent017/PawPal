package com.bulentoral.pawpal.util;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.StorageReference;

import android.content.Context;
import android.widget.ImageView;
import java.io.File;

public class GlideExtensions {
    public static void loadSquareImageFromURL(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop(); // This line ensures the image is centered and cropped

        File file = new File(url);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
    public static void loadSquareImageFromStorageReference(Context context, StorageReference storageReference, ImageView imageView) {
        Glide.with(context)
                .load(storageReference)
                .centerCrop()
                .into(imageView);
    }


    public static void loadCircularImageFromURL(Context context, String url, ImageView imageView) { // fiel path can be URL
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop(); // Bu satır resmi yuvarlak yapar

        File file = new File(url);

        Glide.with(context)
                .load(file)
                .apply(options)
                .into(imageView);
    }

    public static void loadCircularImageFromDrawable(Context context, int drawableId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop(); // Bu satır resmi yuvarlak yapar

        Glide.with(context)
                .load(drawableId)
                .apply(options)
                .into(imageView);
    }
}

