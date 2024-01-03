package com.bulentoral.pawpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bulentoral.pawpal.databinding.ActivitySplashBinding;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
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


//        if(FirebaseUtil.getInstance().isUserLoggedIn()) {
//            NavigationUtils.navigateToActivity(this, MainActivity.class);
//        }
//        else {
//            NavigationUtils.navigateToActivity(SplashActivity.this, AuthActivity.class);
//        }

        initPushNotification();

    }

    private void initPushNotification() {
        if(getIntent().getExtras()!=null){
            Log.d("test","initPushNotification");
            //from notification
            String userId = getIntent().getExtras().getString("userId");
            FirebaseUtil.allUserCollectionReference().document(userId).get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            UserModel model = task.getResult().toObject(UserModel.class);

                            Bundle bundle = AndroidUtil.passUserModelAsBundle(model);

                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putExtras(bundle); // Bundle'ı Intent'e ekleyin
                            startActivity(intent);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            finish();
                        }
                    });


        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(FirebaseUtil.getInstance().isUserLoggedIn()){
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this,AuthActivity.class));
                    }
                    finish();
                }
            },1000);
        }
    }

    private  void initUI(){
        imageView = binding.iconImage;

        GlideExtensions.loadCircularImageFromDrawable(this, R.drawable.puppy, imageView);

    }

}