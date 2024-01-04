package com.bulentoral.pawpal.ui.lost;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentHomeBinding;
import com.bulentoral.pawpal.databinding.FragmentLostAnimalBinding;
import com.bulentoral.pawpal.ui.adopt.AdoptAnimalViewModel;
import com.bulentoral.pawpal.ui.adopt.adapter.PostAdoptationAdapter;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class LostAnimalFragment extends Fragment {
    private FragmentLostAnimalBinding binding;
    private FloatingActionButton addButton;
    private AdoptAnimalViewModel viewModel;
    private ProgressBar progressBar;
    private PostAdoptationAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLostAnimalBinding.inflate(inflater, container, false);

        initUI();
        return binding.getRoot();
    }

    private void initUI() {

        progressBar = binding.progressBar2;
        progressBar.setVisibility(View.VISIBLE);

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
