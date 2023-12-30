package com.bulentoral.pawpal.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentLoginBinding;
import com.bulentoral.pawpal.util.GlideExtensions;
import com.bulentoral.pawpal.util.NavigationUtils;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private ImageView imageView;

    private TextView createButton;

    private Button loginButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
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
        loginButton = binding.loginButton;
        createButton = binding.createButton;
        GlideExtensions.loadCircularImageFromDrawable(requireActivity(), R.drawable.puppy, imageView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtils.navigateToFragment(LoginFragment.this, R.id.action_loginFragment_to_signUpFragment);
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtils.navigateToFragment(LoginFragment.this, R.id.action_loginFragment_to_signUpFragment);
            }
        });

    }

}