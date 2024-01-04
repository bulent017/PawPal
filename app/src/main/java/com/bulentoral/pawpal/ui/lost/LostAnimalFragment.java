package com.bulentoral.pawpal.ui.lost;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentHomeBinding;
import com.bulentoral.pawpal.databinding.FragmentLostAnimalBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.AdoptAnimalViewModel;
import com.bulentoral.pawpal.ui.adopt.AnimalPostClickListener;
import com.bulentoral.pawpal.ui.adopt.HomeFragment;
import com.bulentoral.pawpal.ui.adopt.HomeFragmentDirections;
import com.bulentoral.pawpal.ui.adopt.OnAdoptAnimalsFetchedListener;
import com.bulentoral.pawpal.ui.adopt.adapter.PostAdoptationAdapter;
import com.bulentoral.pawpal.ui.lost.adapter.PostLostAdapter;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class LostAnimalFragment extends Fragment {
    private FragmentLostAnimalBinding binding;
    private FloatingActionButton addButton;
    private LostAnimalViewModel viewModel;
    private ProgressBar progressBar;
    private PostLostAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLostAnimalBinding.inflate(inflater, container, false);

        initUI();
        viewModel = new ViewModelProvider(this).get(LostAnimalViewModel.class);

        // Verileri çek ve UI'ı güncelle
        viewModel.fetchLostAnimalPosts(new OnLostAnimalsFetchedListener() {
            @Override
            public void onFetched(List<PostLostAnimal> lostAnimalPosts) {
                progressBar.setVisibility(View.GONE);
                // Başarılı veri çekme işlemi
                // Burada RecyclerView adapterınızı güncelleyin veya UI'da gösterin
                adapter = new PostLostAdapter(lostAnimalPosts,getActivity(), new AnimalLostPostClickListener() {

                    @Override
                    public void onMovieClicked(String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri, String address, String dateLost, double award) {
                        if (postID != null && userID != null && name != null && type != null && genus != null && gender != null && description != null && imageUri != null && address != null &&dateLost != null) {
                            String[] data = {postID, userID, name, type, genus, String.valueOf(age), gender, description, imageUri, address, dateLost, String.valueOf(award)};


                            LostAnimalFragmentDirections.ActionLostAnimalFragmentToInfoLostAnimalFragment action = LostAnimalFragmentDirections.actionLostAnimalFragmentToInfoLostAnimalFragment(data);
                            NavigationUtils.navigateToFragment(LostAnimalFragment.this, action);


                        } else {
                            // null check
                        }
                    }
                });
                binding.recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(Exception e) {
                // Hata durumu
                // Hata mesajını gösterin veya loglayın
            }
        });

        return binding.getRoot();
    }

    private void initUI() {

        progressBar = binding.progressBar2;
        progressBar.setVisibility(View.VISIBLE);

        binding.floatingActionButtonLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtil.makeNavigationBarInvisible(getActivity());
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
