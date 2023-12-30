package com.bulentoral.pawpal.ui.adopt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bulentoral.pawpal.databinding.FragmentAnimalAdoptFormBinding;
import com.bulentoral.pawpal.databinding.FragmentInfoLostAnimalBinding;
import com.bulentoral.pawpal.util.NavigationUtils;


public class AnimalAdoptFormFragment extends Fragment {
    private FragmentAnimalAdoptFormBinding binding;
    private Button saveButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnimalAdoptFormBinding.inflate(inflater, container, false);
        initUI();
        return binding.getRoot();
    }

    private void initUI() {
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(AnimalAdoptFormFragment.this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }

}