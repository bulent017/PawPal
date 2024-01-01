package com.bulentoral.pawpal.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bulentoral.pawpal.MainActivity;
import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentSignUpBinding;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.GlideExtensions;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;


public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
    private AuthViewModel authViewModel;
    private ImageView imageView;
    private EditText nameEditText;
    private EditText surNameEditText;
    private EditText eMail;
    private EditText password;
    private EditText userNameEditText;

    private UserModel userModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        initUI();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Binding'i temizleme
    }
    private  void initUI(){
        imageView = binding.iconImage;
        GlideExtensions.loadCircularImageFromDrawable(requireActivity(), R.drawable.cat, imageView);
        nameEditText = binding.userName;
        surNameEditText = binding.userSurname;
        eMail = binding.userMail;
        password = binding.userPassword;
        userNameEditText = binding.userNameEditText;

        binding.haveAnAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateToFragment(SignUpFragment.this,R.id.action_signUpFragment_to_loginFragment);
            }
        });

       binding.signUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String email = eMail.getText().toString().trim();
               String pass = password.getText().toString().trim();
               String name = nameEditText.getText().toString().trim();
               String surName = surNameEditText.getText().toString().trim();
               String userName = userNameEditText.getText().toString().trim();

               authViewModel.registerUser(email, pass, new AuthViewModel.OnAuthListener() {
                   @Override
                   public void onSuccess() {

                       userModel = new UserModel(email,userName,Timestamp.now(),FirebaseUtil.currentUserId(),name,surName);
                       signInToFireStore(userModel);

                       //başarılı bir şekilde kayıt olunması durumunda burda kişinin isim ve soyisimi
                       //firestore'a kayıt edilecek!
                   }

                   @Override
                   public void onError(Throwable throwable) {
                       Toast.makeText(requireContext(), "Kayıt başarısız: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();

                   }
               });
           }
       });

    }

    private void signInToFireStore(UserModel userModel) {

        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    NavigationUtils.navigateToActivity(requireActivity(), MainActivity.class);
                }
            }
        });
    }

}