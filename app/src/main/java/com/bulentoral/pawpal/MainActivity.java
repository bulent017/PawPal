package com.bulentoral.pawpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bulentoral.pawpal.databinding.ActivityMainBinding;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.ui.adopt.HomeFragment;
import com.bulentoral.pawpal.ui.chat.MessageFragment;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    //XzHI2SxsRCDfRlr+Xb1NMw==fZs1df2URce5L8d9
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
        getFCMToken();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","main onStart()");
        Intent intent = getIntent();
        if (intent != null) {
            Log.d("test","main intent dolu");
            Bundle receivedBundle = intent.getExtras();
            if (receivedBundle != null) {
                HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("fragment_home");
                if (homeFragment != null) {
                    homeFragment.gelenVeriyiAl(receivedBundle);
                } else{
                    Log.d("test","fragment boş");
                }
            }
            else {
                Log.d("test","bundle boş");
            }
        }
    }



    private void initUI() {
        bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String token = task.getResult();
                Log.d("baha", token);
                FirebaseUtil.currentUserDetails().update("fcmToken", token);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);
//        MenuItem menuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        if (searchView != null) {
//            searchView.setQueryHint("Type here to search");
//        }
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
        return super.onCreateOptionsMenu(menu);
    }
}