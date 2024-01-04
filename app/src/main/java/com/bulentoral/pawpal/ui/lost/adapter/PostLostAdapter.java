package com.bulentoral.pawpal.ui.lost.adapter;

import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bulentoral.pawpal.databinding.ListItemLostAnimalBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.AnimalPostClickListener;
import com.bulentoral.pawpal.ui.adopt.adapter.PostAdoptationAdapter;
import com.bulentoral.pawpal.ui.lost.AnimalLostPostClickListener;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PostLostAdapter extends RecyclerView.Adapter<PostLostAdapter.ViewHolder> {

    private List<PostLostAnimal> postLostAnimalList;
    private AnimalLostPostClickListener animalPostClickListener;

    public  PostLostAdapter(List<PostLostAnimal> lostAnimalList,
                            AnimalLostPostClickListener animalPostClickListener
    ) {
        this.postLostAnimalList = lostAnimalList;
        this.animalPostClickListener = animalPostClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemLostAnimalBinding binding = ListItemLostAnimalBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostLostAnimal postLostAnimal = postLostAnimalList.get(position);
        holder.binding.addressText.setText(postLostAnimal.getAddress());
        loadSquareImageFromURL(holder.binding.imageView.getContext(), postLostAnimal.getImageUri(), holder.binding.imageView);
        holder.binding.descriptionText.setText(postLostAnimal.getDescription());
        holder.binding.datePostedText.setText(convertTimestampToString(postLostAnimal.getDatePosted()));
        holder.binding.awardText.setText( String.valueOf(postLostAnimal.getAward()));
        holder.binding.getRoot().setOnClickListener(view -> {
            if (animalPostClickListener != null && postLostAnimal.getPostID() != null) {
                animalPostClickListener.onMovieClicked(postLostAnimal.getPostID(), postLostAnimal.getUserID(),
                        postLostAnimal.getName(),postLostAnimal.getType(),
                        postLostAnimal.getGenus(),postLostAnimal.getAge(),
                        postLostAnimal.getGender(),postLostAnimal.getDescription(),postLostAnimal.getImageUri(),
                        postLostAnimal.getAddress(),postLostAnimal.getDateLost(),postLostAnimal.getAward());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postLostAnimalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ListItemLostAnimalBinding binding;

        public ViewHolder(ListItemLostAnimalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        Date date = new Date(timestamp.getSeconds() * 1000 + (timestamp.getNanoseconds() / 1000000));

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        return formatter.format(date);
    }
}


