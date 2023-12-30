package com.bulentoral.pawpal.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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
}

