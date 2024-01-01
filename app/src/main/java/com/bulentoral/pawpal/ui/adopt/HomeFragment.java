package com.bulentoral.pawpal.ui.adopt;

import static com.bulentoral.pawpal.util.NavigationUtils.navigateToFragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentHomeBinding;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private FloatingActionButton addButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initUI();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Binding'i temizleme
    }
    private  void initUI(){

        addButton = binding.addAnimalPost;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtils.navigateToFragment(HomeFragment.this, R.id.action_homeFragment_to_animalAdoptFormFragment);
            }
        });

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.iconMessage) {

                    NavigationUtils.navigateToFragment(HomeFragment.this,R.id.action_homeFragment_to_allMessagesFragment);
                    return true;
                } else if (item.getItemId() == R.id.searchUsers) {
                    NavigationUtils.navigateToFragment(HomeFragment.this,R.id.action_homeFragment_to_searchFragment);
                }
                return false;
            }
        });

    }

}