package com.bulentoral.pawpal.ui.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentResetPasswordBinding;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPasswordFragment extends Fragment {

    private Button backButton ;
    private Button resetButton ;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private EditText emailText;
    private  String email;
    private FragmentResetPasswordBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        initUI();
        return binding.getRoot();
    }

    private void initUI(){

        backButton = binding.backButton;
        resetButton = binding.resetPasswordButton;
        progressBar = binding.progressBar;
        emailText = binding.userMail;
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 email =  emailText.getText().toString();
                if (!TextUtils.isEmpty(email)){
                    resetPassword();
                }
                else{
                    emailText.setError("Email fiield can't be empty");
                }

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateToFragment(ResetPasswordFragment.this,R.id.action_resetPasswordFragment_to_loginFragment);
            }
        });


    }
    private void resetPassword() {
        progressBar.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.INVISIBLE);


        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>(){

            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(requireActivity(),"Reset password link has been sent to your registered email",Toast.LENGTH_SHORT).show();
                NavigationUtils.navigateToFragment(ResetPasswordFragment.this,R.id.action_resetPasswordFragment_to_loginFragment);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireActivity(),"Error :" + e.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE );
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
