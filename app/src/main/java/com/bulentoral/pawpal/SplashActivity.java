package com.bulentoral.pawpal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bulentoral.pawpal.databinding.ActivitySplashBinding;
import com.bulentoral.pawpal.util.GlideExtensions;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
    }

    private  void initUI(){
        imageView = binding.iconImage;

        GlideExtensions.loadCircularImageFromDrawable(this, R.drawable.puppy, imageView);

    }

}