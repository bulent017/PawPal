package com.bulentoral.pawpal.ui.adopt;

import static com.bulentoral.pawpal.util.NavigationUtils.navigateToFragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentHomeBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.ui.adopt.adapter.PostAdoptationAdapter;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private FloatingActionButton addButton;
    private AdoptAnimalViewModel viewModel;

    private PostAdoptationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        initUI();
        viewModel = new ViewModelProvider(this).get(AdoptAnimalViewModel.class);

        // Verileri çek ve UI'ı güncelle
        viewModel.fetchAdoptAnimalPosts(new OnAdoptAnimalsFetchedListener() {
            @Override
            public void onFetched(List<PostAdoptAnimal> adoptAnimalPosts) {
                // Başarılı veri çekme işlemi
                // Burada RecyclerView adapterınızı güncelleyin veya UI'da gösterin
                adapter = new PostAdoptationAdapter(adoptAnimalPosts, new AnimalPostClickListener() {
                    @Override
                    public void onMovieClicked(String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri, String address) {
                        if (postID != null && userID != null && name != null && type != null && genus != null && gender != null && description != null && imageUri != null && address != null) {
                            String[] data = {postID, userID, name, type, genus, String.valueOf(age), gender, description, imageUri, address};


                            HomeFragmentDirections.ActionHomeFragmentToInfoAdoptFragment action = HomeFragmentDirections.actionHomeFragmentToInfoAdoptFragment(data);
                            NavigationUtils.navigateToFragment(HomeFragment.this, action);


                        } else {
                            // Verilerden en az biri null, bu durumu ele almak için gerekli işlemleri yapabilirsiniz.
                        }

                    }
                });
                binding.recyclerview.setAdapter(adapter);

            }

            @Override
            public void onError(Exception e) {
                // Hata durumu
                // Hata mesajını gösterin veya loglayın
            }
        });

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
                }
                return false;
            }
        });

    }

}