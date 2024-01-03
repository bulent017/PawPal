package com.bulentoral.pawpal.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bulentoral.pawpal.MainActivity;
import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.model.ChatroomModel;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.ui.chat.MessageFragment;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchUserRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel, SearchUserRecyclerAdapter.UserModelViewHolder> {

    Context context;
    private OnItemClickListener listener;

    public SearchUserRecyclerAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options, Context context,OnItemClickListener listener ) {
        super(options);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull UserModel model) {
        holder.usernameText.setText(model.getUsername());
        holder.emailText.setText(model.geteMail());
        if(model.getUserId().equals(FirebaseUtil.currentUserId())){
            holder.usernameText.setText(model.getUsername()+" (Me)");
        }

//        FirebaseUtil.getOtherProfilePicStorageRef(model.getUserId()).getDownloadUrl()
//                .addOnCompleteListener(t -> {
//                    if(t.isSuccessful()){
//                        Uri uri  = t.getResult();
//                        AndroidUtil.setProfilePic(context,uri,holder.profilePic);
//                    }
//                });

        holder.itemView.setOnClickListener(v -> {

            listener.onItemClick(model);
            // Navigate to target fragment

        });
    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new UserModelViewHolder(view);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView emailText;
        ImageView profilePic;

        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            emailText = itemView.findViewById(R.id.last_message_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserModel userModel);
    }
}
