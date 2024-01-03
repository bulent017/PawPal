package com.bulentoral.pawpal.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bulentoral.pawpal.R;

public class NavigationUtils {

    // Activity'ler arası geçiş için fonksiyon
    public static <T extends AppCompatActivity> void navigateToActivity(Context context, Class<T> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    // Fragmentlar arası geçiş için fonksiyon
    public static void navigateToFragment(Fragment fragment, int actionId) {
        NavHostFragment.findNavController(fragment).navigate(actionId);
    }

    public static void navigateUp(Fragment fragment) {
        NavHostFragment.findNavController(fragment).navigateUp();
    }

    // Argümanlar ile veri taşınacaksa bunu kullan, method overloading
    public static void navigateToFragment(Fragment fragment, NavDirections directions) {
        NavHostFragment.findNavController(fragment).navigate(directions);
    }
    public static void navigateToFragment(Fragment fragment, int directions, Bundle bundle) {
        //NavHostFragment navHostFragment = (NavHostFragment) fragment.getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        //NavController navController = navHostFragment.getNavController();

        // Eğer bir Bundle varsa, NavController'e bu Bundle'ı ekleyerek navigasyon yapın
        if (bundle != null) {
          NavHostFragment.findNavController(fragment).navigate(directions, bundle);
        } else {
            NavHostFragment.findNavController(fragment).navigate(directions);
        }
    }

}

