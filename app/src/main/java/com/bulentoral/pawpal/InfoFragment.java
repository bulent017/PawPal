package com.bulentoral.pawpal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.databinding.FragmentInfoBinding;
import com.bulentoral.pawpal.service.AnimalClient;
import com.bulentoral.pawpal.service.AnimalResponse;
import com.bulentoral.pawpal.util.NavigationUtils;

import java.util.Objects;


public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        binding.group.setVisibility(View.INVISIBLE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();

    }

    private void initUI() {

        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(InfoFragment.this);
            }
        });


        binding.searchAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animalName = Objects.requireNonNull(binding.animalEditText.getText()).toString();

                if (isNameValid(animalName)) {
                    AnimalClient.someMethod(animalName, new AnimalResponseCallback() {
                        @Override
                        public void onSuccess(AnimalResponse animalResponse) {
                            // Burada başarılı bir şekilde alınan AnimalResponse objesiyle işlem yapabilirsiniz.
                            System.out.println("Animal name: " + animalResponse.getName());
                            initFeatures(animalResponse);

                        }

                        @Override
                        public void onFailure(String errorMessage) {
                            // Hata durumunda bu metot çağrılır.
                            System.out.println("Error: " + errorMessage);
                        }
                    });
                }

            }
        });
    }

    private void initFeatures(AnimalResponse animalResponse) {
        binding.group.setVisibility(View.VISIBLE);
        if(animalResponse.getName()!=null) {
            binding.nameAnimalInfo.setText(animalResponse.getName());
        } else {
            binding.nameAnimalInfo.setText("NONE");
        }

        if(animalResponse.getTaxonomy().getScientific_name()!=null) {
            binding.sciNameAnimalInfo.setText(animalResponse.getTaxonomy().getScientific_name());
        } else {
            binding.sciNameAnimalInfo.setText("NONE");
        }

        if(animalResponse.getCharacteristics().getBiggest_threat()!=null) {
            binding.threatAnimalInfo.setText(animalResponse.getCharacteristics().getBiggest_threat());
        } else {
            binding.threatAnimalInfo.setText("NONE");
        }

        if(animalResponse.getCharacteristics().getHabitat()!=null) {
            binding.habitatAnimalInfo.setText(animalResponse.getCharacteristics().getHabitat());
        } else {
            binding.habitatAnimalInfo.setText("NONE");
        }

        if(animalResponse.getCharacteristics().getDiet()!=null) {
            binding.dietAnimalInfo.setText(animalResponse.getCharacteristics().getDiet());
        } else {
            binding.dietAnimalInfo.setText("NONE");
        }

        if(animalResponse.getCharacteristics().getSlogan()!=null) {
            binding.generalAnimalInfo.setText(animalResponse.getCharacteristics().getSlogan());
        } else {
            binding.generalAnimalInfo.setText("NONE");
        }
        binding.animalEditText.setText("");

    }

    public interface AnimalResponseCallback {
        void onSuccess(AnimalResponse animalResponse);
        void onFailure(String errorMessage);
    }


    public boolean isNameValid(String animalName) {
        return animalName.length() >= 3;
    }
}