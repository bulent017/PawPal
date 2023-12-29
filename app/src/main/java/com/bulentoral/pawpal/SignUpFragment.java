package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bulentoral.pawpal.databinding.FragmentLoginBinding;
import com.bulentoral.pawpal.databinding.FragmentSignUpBinding;
import com.bulentoral.pawpal.util.GlideExtensions;


public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
    private ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
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

    }

}