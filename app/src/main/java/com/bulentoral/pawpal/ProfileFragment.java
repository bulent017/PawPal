package com.bulentoral.pawpal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bulentoral.pawpal.databinding.FragmentProfileBinding;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.service.AnimalClient;
import com.bulentoral.pawpal.service.AnimalResponse;
import com.bulentoral.pawpal.service.Taxonomy;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;


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
                    FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                FirebaseUtil.getInstance().logout();
                                Intent intent = new Intent(getContext(),SplashActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }
                    });
//                    FirebaseUtil.getInstance().logout();
//                    Toast.makeText(requireContext(),"Logged Out!!",Toast.LENGTH_LONG).show();
//                    NavigationUtils.navigateToActivity(requireActivity(),AuthActivity.class);
                    return true;
                }
                return false;
            }
        });
        setUIFromFirebase();



    }

    private void setUIFromFirebase() {

        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                UserModel currentUser = task.getResult().toObject(UserModel.class);
                try{
                    String fullName = currentUser.getName()+ " " + currentUser.getSurName();

                    binding.profileName.setText(fullName);
                    binding.profileEmail.setText(currentUser.geteMail());
                    binding.userNameProfile.setText(currentUser.getUsername());


                }catch (Exception e){
                    Log.d("test","Bilgiler alinirken error verdi: " + e);
                }

            } else {

                Log.d("test"," bilgiler alinamadı");

            }
        });
    }

}