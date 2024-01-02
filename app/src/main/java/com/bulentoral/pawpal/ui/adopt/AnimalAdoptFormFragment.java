package com.bulentoral.pawpal.ui.adopt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentAnimalAdoptFormBinding;
import com.bulentoral.pawpal.model.Post;
import com.bulentoral.pawpal.model.PostType;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class AnimalAdoptFormFragment extends Fragment {
    private static final String TAG = "AnimalAdoptFormFragment";
    private FragmentAnimalAdoptFormBinding binding;
    private Button saveButton;

    private EditText animalName;
    private EditText animalType;
    private EditText animalGenus;
    private EditText animalAge;
    private RadioGroup radioGroup;
    private EditText animalDescription;
    private ImageView animalPicture;
    private Uri imageUri;
    private final PostType postType= PostType.ADOPTION;
    private AutoCompleteTextView addressAutoCompleteTextView;

    private  AdoptAnimalViewModel animalViewModel;
    private ActivityResultLauncher<Intent> startForProfileImageResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    Intent data = result.getData();

                    if (resultCode == Activity.RESULT_OK) {
                        // Image Uri will not be null for RESULT_OK
                        imageUri = data.getData();
                        animalPicture.setImageURI(imageUri);
                    } else if (resultCode == ImagePicker.RESULT_ERROR) {
                        Toast.makeText(getActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnimalAdoptFormBinding.inflate(inflater, container, false);
        initUI();
        animalViewModel = new AdoptAnimalViewModel();
        return binding.getRoot();
    }

    private void initUI() {

        saveButton = binding.saveButton;
        animalName = binding.animalNameText;
        animalAge = binding.animalAgeText;
        animalDescription = binding.infoExtraText;
        radioGroup = binding.radioGroup;
        animalGenus = binding.animalCinsText;
        addressAutoCompleteTextView = binding.adressAutoCompleteTextview;
        animalPicture = binding.animalPicture;
        animalType = binding.animalTypeText;
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Umarım kaydeder");
                savePostFirebase();

            }
        });

        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(AnimalAdoptFormFragment.this);
            }
        });

        binding.addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(requireActivity())
                        .crop()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .createIntent(new Function1<Intent, Unit>() {
                            @Override
                            public Unit invoke(Intent intent) {
                                startForProfileImageResult.launch(intent);
                                return null;
                            }
                        });
            }
        });


    }

    private void savePostFirebase() {

        String name = animalName.getText().toString();
        int age = Integer.parseInt(animalAge.getText().toString());
        String description = animalDescription.getText().toString();
        String genus = animalGenus.getText().toString();
        String type = animalType.getText().toString();
        //String address = addressAutoCompleteTextView.getText().toString();
        String gender = radioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton ? "Male" : "Female";
        String address = "Anakara /Altındağ";
        animalViewModel.uploadImageAndCreatePost(new Post(postType,name,type,genus,age,gender,description,imageUri.toString(),address));

        Log.d("AnimalAdoptFormFragment"+"savePostFirebase", imageUri.toString());
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }

}