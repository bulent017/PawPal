package com.bulentoral.pawpal.util;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import android.content.Context;
import android.widget.ImageView;
import java.io.File;

public class GlideExtensions {

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

