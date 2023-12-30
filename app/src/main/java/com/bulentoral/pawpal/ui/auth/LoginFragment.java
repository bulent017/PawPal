package com.bulentoral.pawpal.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bulentoral.pawpal.AuthActivity;
import com.bulentoral.pawpal.MainActivity;
import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.SplashActivity;
import com.bulentoral.pawpal.databinding.FragmentLoginBinding;
import com.bulentoral.pawpal.util.GlideExtensions;
import com.bulentoral.pawpal.util.NavigationUtils;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private AuthViewModel authViewModel;
    private ImageView imageView;
    private EditText eMail;
    private EditText password;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
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
        GlideExtensions.loadCircularImageFromDrawable(requireActivity(), R.drawable.puppy, imageView);

        eMail = binding.userMail;
        password = binding.userPassword;
        loginButton = binding.loginButton;

        binding.toCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateToFragment(LoginFragment.this,R.id.action_loginFragment_to_signUpFragment);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = eMail.getText().toString().trim();
                String pass = password.getText().toString().trim();

                // ViewModel'den kullanıcı girişi işlemi başlatma
                authViewModel.loginUser(email, pass, new AuthViewModel.OnAuthListener() {
                    @Override
                    public void onSuccess() {
                        // Giriş başarılı ise yapılacak işlemler (örn. ana ekrana yönlendirme)
                        NavigationUtils.navigateToActivity(requireActivity(), MainActivity.class);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        // Giriş başarısız ise kullanıcıya hata mesajı gösterme
                        Toast.makeText(requireContext(), "Giriş başarısız: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}