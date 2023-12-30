package com.bulentoral.pawpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bulentoral.pawpal.databinding.ActivitySplashBinding;
import com.bulentoral.pawpal.util.FirebaseSource;
import com.bulentoral.pawpal.util.GlideExtensions;
import com.bulentoral.pawpal.util.NavigationUtils;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
        if(FirebaseSource.getInstance().isUserLoggedIn()) {
            NavigationUtils.navigateToActivity(this, MainActivity.class);
        }
        else {
            NavigationUtils.navigateToActivity(SplashActivity.this, AuthActivity.class);
        }

    }

    private  void initUI(){
        imageView = binding.iconImage;

        GlideExtensions.loadCircularImageFromDrawable(this, R.drawable.puppy, imageView);

    }

}