package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.databinding.FragmentLostAnimalBinding;


public class LostAnimalFragment extends Fragment {
    private FragmentLostAnimalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLostAnimalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }
}
