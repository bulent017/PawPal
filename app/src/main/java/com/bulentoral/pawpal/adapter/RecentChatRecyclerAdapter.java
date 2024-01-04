package com.bulentoral.pawpal.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.model.ChatroomModel;
import com.bulentoral.pawpal.model.UserModel;
import com.bulentoral.pawpal.util.AndroidUtil;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.bulentoral.pawpal.util.NavigationUtils;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecentChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatroomModel, RecentChatRecyclerAdapter.ChatroomModelViewHolder> {

    private Context context;
    private OnItemClickListener listener;

    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatroomModel> options, Context context, OnItemClickListener listener) {
        super(options);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatroomModelViewHolder holder, int position, @NonNull ChatroomModel model) {
        FirebaseUtil.getOtherUserFromChatroom(model.getUserIds())
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean lastMessageSentByMe = model.getLastMessageSenderId().equals(FirebaseUtil.currentUserId());


                        UserModel otherUserModel = task.getResult().toObject(UserModel.class);
                        if(otherUserModel!=null) {
                            System.out.println(otherUserModel);
                        } else {
                            System.out.println("hata!!!: otherUserModel boş geldi :(");
                        }

//                        FirebaseUtil.getOtherProfilePicStorageRef(otherUserModel.getUserId()).getDownloadUrl()
//                                .addOnCompleteListener(t -> {
//                                    if (t.isSuccessful()) {
//                                        Uri uri = t.getResult();
//                                        AndroidUtil.setProfilePic(context, uri, holder.profilePic);
//                                    }
//                                });


                        holder.usernameText.setText(otherUserModel.getUsername());

                        if (lastMessageSentByMe) {
                            holder.lastMessageText.setText("You : " + model.getLastMessage());
                        }
                        else {
                            holder.lastMessageText.setText(model.getLastMessage());
                        }

                        holder.lastMessageTime.setText(FirebaseUtil.timestampToString(model.getLastMessageTimestamp()));

                        holder.itemView.setOnClickListener(v -> {
                            listener.onItemClick(model, otherUserModel);
                        });


                    }
                });
    }

    @NonNull
    @Override
    public ChatroomModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row, parent, false);
        return new ChatroomModelViewHolder(view);
    }

    class ChatroomModelViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        TextView lastMessageText;
        TextView lastMessageTime;
        ImageView profilePic;

        public ChatroomModelViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameText = itemView.findViewById(R.id.user_name_text);
            lastMessageText = itemView.findViewById(R.id.last_message_text);
            lastMessageTime = itemView.findViewById(R.id.last_message_time_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }

    }
   public interface OnItemClickListener {
        void onItemClick(ChatroomModel chatroomModel, UserModel userModel);
    }
}