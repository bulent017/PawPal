package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.databinding.FragmentLostAnimalBinding;
import com.bulentoral.pawpal.util.NavigationUtils;


public class LostAnimalFragment extends Fragment {
    private FragmentLostAnimalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLostAnimalBinding.inflate(inflater, container, false);

        initUI();
        return binding.getRoot();
    }

    private void initUI() {

        binding.floatingActionButtonLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateToFragment(LostAnimalFragment.this, R.id.action_lostAnimalFragment_to_animalLostFormFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }
}
