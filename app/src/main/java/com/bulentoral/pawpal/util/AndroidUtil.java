package com.bulentoral.pawpal.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bulentoral.pawpal.model.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AndroidUtil {
    public static void passUserModelAsIntent(Intent intent, UserModel model){
        intent.putExtra("username",model.getUsername());
        intent.putExtra("email",model.geteMail());
        intent.putExtra("userId",model.getUserId());
        intent.putExtra("fcmToken",model.getFcmToken());

    }

    public static Bundle passUserModelAsBundle(UserModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("username", model.getUsername());
        bundle.putString("email", model.geteMail());
        bundle.putString("userId", model.getUserId());
        bundle.putString("fcmToken", model.getFcmToken());
        bundle.putString("name",model.getName());
        bundle.putString("surname",model.getSurName());
        return bundle;
    }

    public static UserModel getUserModelFromBundle(Bundle bundle) {
        if (bundle != null) {
            String username = bundle.getString("username");
            String email = bundle.getString("email");
            String userId = bundle.getString("userId");
            String fcmToken = bundle.getString("fcmToken");
            String name = bundle.getString("name");
            String surname = bundle.getString("surname");

            UserModel userModel = new UserModel();
            userModel.seteMail(email);
            userModel.setUserId(userId);
            userModel.setUsername(username);
            userModel.setFcmToken(fcmToken);
            userModel.setName(name);
            userModel.setSurName(surname);

            return userModel;
        }
        return null;  // Eğer bundle null ise null döndürülebilir ya da başka bir hata işleme yöntemi kullanılabilir.
    }



    public static UserModel getUserModelFromIntent(Intent intent){
        UserModel userModel = new UserModel();
        userModel.setUsername(intent.getStringExtra("username"));
        userModel.seteMail(intent.getStringExtra("email"));
        userModel.setUserId(intent.getStringExtra("userId"));
        userModel.setFcmToken(intent.getStringExtra("fcmToken"));
        return userModel;
    }

    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
