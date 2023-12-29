package com.bulentoral.pawpal;

import static com.bulentoral.pawpal.util.NavigationUtils.navigateToFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    }

}