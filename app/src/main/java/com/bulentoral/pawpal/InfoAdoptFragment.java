package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.databinding.FragmentAnimalLostFormBinding;
import com.bulentoral.pawpal.databinding.FragmentInfoAdoptBinding;
import com.bulentoral.pawpal.util.NavigationUtils;


public class InfoAdoptFragment extends Fragment {
    private FragmentInfoAdoptBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoAdoptBinding.inflate(inflater, container, false);
        initUI();

        return binding.getRoot();
    }

    private void initUI() {
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(InfoAdoptFragment.this);
            }
        });
    }


}