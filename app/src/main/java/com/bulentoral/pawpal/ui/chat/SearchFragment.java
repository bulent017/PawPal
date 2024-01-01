package com.bulentoral.pawpal.ui.chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.adapter.SearchUserRecyclerAdapter;
import com.bulentoral.pawpal.databinding.FragmentSearchBinding;
import com.bulentoral.pawpal.model.ChatroomModel;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;


public class SearchFragment extends Fragment implements SearchUserRecyclerAdapter.OnItemClickListener {

    private FragmentSearchBinding binding;
    EditText searchInput;
    ImageButton searchButton;
    ImageButton backButton;
    RecyclerView recyclerView;

    SearchUserRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false);

        searchInput = binding.seachUsernameInput;
        searchButton = binding.searchUserBtn;
        backButton = binding.backBtn;
        recyclerView = binding.searchUserRecyclerView;

        searchInput.requestFocus();

        searchButton.setOnClickListener(v -> {
            String searchTerm = searchInput.getText().toString();
            if(searchTerm.isEmpty() || searchTerm.length()<3){
                searchInput.setError("Invalid Username");
                return;
            }
            setupSearchRecyclerView(searchTerm);
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(SearchFragment.this);
            }
        });

        return binding.getRoot();
    }


    void setupSearchRecyclerView(String searchTerm){

        Query query = FirebaseUtil.allUserCollectionReference()
                .whereGreaterThanOrEqualTo("username",searchTerm)
                .whereLessThanOrEqualTo("username",searchTerm+'\uf8ff');

        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query,UserModel.class).build();

        adapter = new SearchUserRecyclerAdapter(options,requireContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onItemClick(UserModel userModel) {
        // UserModel'den Bundle oluşturma
        Bundle userModelBundle = AndroidUtil.passUserModelAsBundle(userModel);

        // NavigationUtils.navigateToFragment metodu ile navigasyon işlemini gerçekleştirin
        NavigationUtils.navigateToFragment(this, R.id.action_searchFragment_to_messageFragment, userModelBundle);
    }
}