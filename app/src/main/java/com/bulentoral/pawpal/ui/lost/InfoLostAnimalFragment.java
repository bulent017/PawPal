package com.bulentoral.pawpal.ui.lost;

import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.FragmentInfoLostAnimalBinding;
import com.bulentoral.pawpal.databinding.FragmentLostAnimalBinding;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.ui.adopt.InfoAdoptFragmentArgs;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;

public class InfoLostAnimalFragment extends Fragment {
    private FragmentInfoLostAnimalBinding binding;
    private EditText name;
    private EditText address;
    private EditText type;
    private EditText genus;
    private EditText age;
    private EditText gender;
    private EditText description;
    private EditText lostDate;
    private EditText awardText;
    private ImageView imaegView2;

    private Button contactButton;
    private String postUserID;
    private String postID;

    private UserModel otherUserModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoLostAnimalBinding.inflate(inflater, container, false);

        initUI();

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
        imaegView2 = binding.imageView2;
        contactButton = binding.contactLostButton;
        lostDate = binding.animalLostDateText;
        awardText = binding.infoAwardText;
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(InfoLostAnimalFragment.this);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
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
        setDataToUI();
    }
    public UserModel findUserModel(String userId) {

        FirebaseUtil.getUserWithId(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                otherUserModel = task.getResult().toObject(UserModel.class);
                try {
                    String fullName = otherUserModel.getName() + " " + otherUserModel.getSurName();
                    Log.d("test3", fullName);
                    Bundle userModelBundle = AndroidUtil.passUserModelAsBundle(otherUserModel);
                    NavigationUtils.navigateToFragment(this, R.id.action_infoLostAnimalFragment_to_messageFragment, userModelBundle);
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
            InfoLostAnimalFragmentArgs args = InfoLostAnimalFragmentArgs.fromBundle(getArguments());
            String[] data = args.getDataInfo();

            System.out.println(data[0].toString());
            //  postID, userID, name, type, genus, String.valueOf(age), gender, description, imageUri, address, dateLost, String.valueOf(award)
            postUserID = data[1];
            postID = data[0];
            name.setText(data[2]);
            type.setText(data[3]);
            genus.setText(data[4]);
            description.setText(data[7]);
            address.setText(data[9]);
            age.setText(data[5]);
            gender.setText(data[6]);
            lostDate.setText(data[10]);
            System.out.println(data[10]);
            awardText.setText(data[11]);
            loadSquareImageFromURL(imaegView2.getContext(), data[8], imaegView2);

        }

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // önemli: hafıza sızıntısını önlemek için
    }
}
