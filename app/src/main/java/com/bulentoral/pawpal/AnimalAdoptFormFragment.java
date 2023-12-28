package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bulentoral.pawpal.databinding.FragmentAnimalAdoptFormBinding;
import com.bulentoral.pawpal.databinding.FragmentInfoLostAnimalBinding;



public class AnimalAdoptFormFragment extends Fragment {
    private FragmentAnimalAdoptFormBinding binding;
    private Button saveButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnimalAdoptFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }

    private  void initUI() {


    }

}