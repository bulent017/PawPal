package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bulentoral.pawpal.databinding.FragmentInfoAdoptBinding;
import com.bulentoral.pawpal.databinding.FragmentProfileBinding;
import com.bulentoral.pawpal.util.FirebaseSource;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;


public class ProfileFragment extends Fragment {

   private FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {

        binding.tabLayoutProfile.addTab(binding.tabLayoutProfile.newTab().setText("Adopt"),0);
        binding.tabLayoutProfile.addTab(binding.tabLayoutProfile.newTab().setText("Lost Animal"),1);

        binding.tabLayoutProfile.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab != null) {
                    int position = tab.getPosition();
                    // ViewSwitcher'ın görüntüsünü değiştir
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Bu örnekte herhangi bir şey yapmıyoruz
                if (tab != null) {
                    int position = tab.getPosition();

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Bu örnekte herhangi bir şey yapmıyoruz
            }
        });


        binding.materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.editProfile) {
                    NavigationUtils.navigateToFragment(ProfileFragment.this,R.id.action_profileFragment_to_editProfileFragment);

                    return true;
                } else if (id == R.id.logOut) {
                    // Log Out seçeneği seçildiğinde yapılacak işlemler
                    FirebaseSource.getInstance().logout();
                    Toast.makeText(requireContext(),"Logged Out!!",Toast.LENGTH_LONG).show();
                    NavigationUtils.navigateToActivity(requireActivity(),AuthActivity.class);
                    return true;
                }
                return false;
            }
        });
    }

}