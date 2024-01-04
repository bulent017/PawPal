package com.bulentoral.pawpal.ui.lost;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentAnimalLostFormBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.AnimalAdoptFormFragment;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class AnimalLostFormFragment extends Fragment {
    private FragmentAnimalLostFormBinding binding;
    private Button saveButton;
    private TextView imageviewWarningText;

    private EditText awardAmount;
    private EditText lostDate;
    private FloatingActionButton addPictureButton;
    private EditText animalName;
    private EditText animalType;
    private EditText animalGenus;
    private EditText animalAge;
    private RadioGroup radioGroup;
    private EditText animalDescription;
    private AutoCompleteTextView addressAutoCompleteTextView;
    private ImageView animalPicture;
    private Uri imageUri;
    private LostAnimalViewModel lostAnimalViewModel;
    String address;
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
                        imageviewWarningText.setVisibility(View.GONE);
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
        binding = FragmentAnimalLostFormBinding.inflate(inflater, container, false);
        initUI();
        lostAnimalViewModel = new LostAnimalViewModel();
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
        animalPicture = binding.imageView2;
        animalType = binding.animalTypeText;
        imageviewWarningText = binding.imageviewWarningText;
        addPictureButton = binding.addPicture;
        lostDate = binding.animalLostDateText;
        awardAmount = binding.infoAwardText;

        Calendar calendar = Calendar.getInstance();

        lostDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Ay değeri 0'dan başladığı için 1 ekliyoruz.
                                monthOfYear += 1;
                                String date = dayOfMonth + "/" + monthOfYear + "/" + year;
                                lostDate.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        imageviewWarningText.setVisibility(View.VISIBLE);
        String[] cities = getResources().getStringArray(R.array.turkey_cities);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_dropdown_item_1line, cities);

        addressAutoCompleteTextView.setAdapter(adapter);
        addressAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = (String) parent.getItemAtPosition(position);

                address = selectedCity;

            }
        });


        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtil.makeNavigationBarVisible(getActivity());
                NavigationUtils.navigateUp(AnimalLostFormFragment.this);
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Umarım kaydeder");
                if (savePostFirebase2()) {
                    NavigationUtils.navigateUp(AnimalLostFormFragment.this);
                }
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


    private boolean savePostFirebase2() {
        String name = animalName.getText().toString();
        String ageStr = animalAge.getText().toString();
        String description = animalDescription.getText().toString();
        String genus = animalGenus.getText().toString();
        String type = animalType.getText().toString();
        String address = addressAutoCompleteTextView.getText().toString();
        String gender = radioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton ? "Male" : "Female";
        String award = awardAmount.getText().toString();
        String animalLostDate = lostDate.getText().toString();

        // ImageView için kontrol
        if (imageUri == null || TextUtils.isEmpty(imageUri.toString())) {
            Toast.makeText(getContext(), "Image cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            animalName.setError("Name cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(ageStr)) {
            animalAge.setError("Age cannot be empty");
            return false;
        }
        int age = Integer.parseInt(ageStr);
        if (TextUtils.isEmpty(description)) {
            animalDescription.setError("Description cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(genus)) {
            animalGenus.setError("Genus cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(type)) {
            animalType.setError("Type cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            addressAutoCompleteTextView.setError("Address cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(animalLostDate)) {
            lostDate.setError("Lost date cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(award)) {
            awardAmount.setError("Award date cannot be empty");
            return false;
        }

       //String name, String type, String genus, int age, String gender, String description, String imageUri, String address, Timestamp dateLost, double award
        lostAnimalViewModel.uploadImageAndCreatePost(new PostLostAnimal(name, type, genus, age, gender, description, imageUri.toString(), address,animalLostDate,Double.parseDouble(award)));

        Log.d("AnimalAdoptFormFragment" + "savePostFirebase", imageUri.toString());

        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }
}
