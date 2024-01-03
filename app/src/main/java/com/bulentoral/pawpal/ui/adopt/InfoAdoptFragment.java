package com.bulentoral.pawpal.ui.adopt;

import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bulentoral.pawpal.databinding.FragmentAnimalLostFormBinding;
import com.bulentoral.pawpal.databinding.FragmentInfoAdoptBinding;
import com.bulentoral.pawpal.util.NavigationUtils;


public class InfoAdoptFragment extends Fragment {
    private FragmentInfoAdoptBinding binding;

    private TextView name;
    private TextView address;
    private TextView type;
    private TextView genus;
    private TextView age;
    private TextView gender;
    private TextView description;
    private ImageView imaegView3;

    private String postUserID;
    private String postID;


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

        name = binding.name;
        type = binding.type;
        genus = binding.genus;
        age = binding.age;
        gender = binding.gender;
        address = binding.address;
        description = binding.descripton;
        imaegView3 = binding.imageView3;






        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(InfoAdoptFragment.this);
            }
        });
    }

    public void setDataToUI(){

        if (getArguments()!=null) {
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
            loadSquareImageFromURL(imaegView3.getContext(), data[8],imaegView3);

        }

    }



}