package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.databinding.FragmentInfoLostAnimalBinding;
import com.bulentoral.pawpal.databinding.FragmentLostAnimalBinding;
import com.bulentoral.pawpal.util.NavigationUtils;

public class InfoLostAnimalFragment extends Fragment {
    private FragmentInfoLostAnimalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoLostAnimalBinding.inflate(inflater, container, false);

        initUI();
        return binding.getRoot();
    }

    private void initUI() {
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(InfoLostAnimalFragment.this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }
}
