package com.bulentoral.pawpal.ui.adopt;

import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentAnimalLostFormBinding;
import com.bulentoral.pawpal.databinding.FragmentInfoAdoptBinding;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;


public class InfoAdoptFragment extends Fragment {
    private FragmentInfoAdoptBinding binding;

    private EditText name;
    private EditText address;
    private EditText type;
    private EditText genus;
    private EditText age;
    private EditText gender;
    private EditText description;
    private ImageView imaegView3;

    private Button contactButton;
    private String postUserID;
    private String postID;

    private UserModel otherUserModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoAdoptBinding.inflate(inflater, container, false);
        initUI();
        setDataToUI();




        return binding.getRoot();
    }

    private void initUI() {

        name = binding.animalNameText;
        type = binding.animalTypeText;
        genus = binding.animalCinsText;
        age = binding.animalAgeText;
        gender = binding.animalGenderText;
        address = binding.infoAddressText;
        description = binding.infoExtraText;
        imaegView3 = binding.animalPicture;
        contactButton = binding.contactButton;
        binding.contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postUserID != null) {
                    Log.d("test3", "postUserId= " + postUserID);
                    findUserModel(postUserID);
//                    Bundle userModelBundle = AndroidUtil.passUserModelAsBundle(findUserModel(postUserID));
//                    NavigationUtils.navigateToFragment(InfoAdoptFragment.this, R.id.action_infoAdoptFragment_to_messageFragment,userModelBundle);
                }
            }
        });




        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AndroidUtil.makeNavigationBarVisible(getActivity());
                NavigationUtils.navigateUp(InfoAdoptFragment.this);
            }
        });
    }

    public UserModel findUserModel(String userId) {

        FirebaseUtil.getUserWithId(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                otherUserModel = task.getResult().toObject(UserModel.class);
                try {
                    String fullName = otherUserModel.getName() + " " + otherUserModel.getSurName();
                    Log.d("test3", fullName);
                    Bundle userModelBundle = AndroidUtil.passUserModelAsBundle(otherUserModel);
                    NavigationUtils.navigateToFragment(this, R.id.action_infoAdoptFragment_to_messageFragment, userModelBundle);
                } catch (Exception e) {
                    Log.d("test3", "Bilgiler alinirken error verdi: " + e);
                }

            } else {
                Log.d("test3", " bilgiler alinamadı");

            }
        });

        return otherUserModel;
    }

    public void setDataToUI() {

        if (getArguments() != null) {
            InfoAdoptFragmentArgs args = InfoAdoptFragmentArgs.fromBundle(getArguments());
            String[] data = args.getInfo();

            System.out.println(data[0].toString());
            //  String[] data = {postID, userID, name, type, genus, String.valueOf(age), gender, description, imageUri, address};
            postUserID = data[1];
            postID = data[0];
            name.setText(data[2]);
            type.setText(data[3]);
            genus.setText(data[4]);
            description.setText(data[7]);
            address.setText(data[9]);
            age.setText(data[5]);
            gender.setText(data[6]);

            loadSquareImageFromURL(imaegView3.getContext(), data[8], imaegView3);

        }

    }



}