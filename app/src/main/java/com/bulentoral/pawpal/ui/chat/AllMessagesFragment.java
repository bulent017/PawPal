package com.bulentoral.pawpal.ui.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.adapter.RecentChatRecyclerAdapter;
import com.bulentoral.pawpal.databinding.FragmentAllMessagesBinding;
import com.bulentoral.pawpal.model.ChatroomModel;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class AllMessagesFragment extends Fragment implements RecentChatRecyclerAdapter.OnItemClickListener{

    private FragmentAllMessagesBinding binding;
    RecyclerView recyclerView;
    RecentChatRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllMessagesBinding.inflate(inflater,container,false);
        recyclerView = binding.chatsRecyclerView;
        initUI();
        setupRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initUI() {

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationUtils.navigateUp(AllMessagesFragment.this);
            }
        });
    }

    void setupRecyclerView(){

        Query query = FirebaseUtil.allChatroomCollectionReference()
                .whereArrayContains("userIds",FirebaseUtil.currentUserId())
                .orderBy("lastMessageTimestamp",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatroomModel> options = new FirestoreRecyclerOptions.Builder<ChatroomModel>()
                .setQuery(query,ChatroomModel.class).build();

        adapter = new RecentChatRecyclerAdapter(options, getContext(), this); // Listener'ı bu şekilde geçin
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ChatroomModel chatroomModel, UserModel userModel) {
        // UserModel'den Bundle oluşturma
        Bundle userModelBundle = AndroidUtil.passUserModelAsBundle(userModel);

        // NavigationUtils.navigateToFragment metodu ile navigasyon işlemini gerçekleştirin
        NavigationUtils.navigateToFragment(this, R.id.action_allMessagesFragment_to_messageFragment, userModelBundle);
    }

}