package com.bulentoral.pawpal.ui.lost.adapter;

import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bulentoral.pawpal.databinding.ItemViewHomeBinding;
import com.bulentoral.pawpal.databinding.ListItemLostAnimalBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.AnimalPostClickListener;
import com.bulentoral.pawpal.ui.adopt.adapter.PostAdoptationAdapter;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
public class PostLostAdapter extends RecyclerView.Adapter<PostLostAdapter.ViewHolder> {

    private List<PostLostAnimal> postLostAnimalList;
    private AnimalPostClickListener animalPostClickListener;

    public  PostLostAdapter(List<PostLostAnimal> movieList,
                                  AnimalPostClickListener animalPostClickListener
    ) {
        this.postLostAnimalList = movieList;
        this.animalPostClickListener = animalPostClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemLostAnimalBinding binding = ListItemLostAnimalBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostAdoptAnimal postAdoptAnimal = postAdoptAnimalList.get(position);
        holder.binding.addressText.setText(postAdoptAnimal.getAddress());
        loadSquareImageFromURL(holder.binding.imageView.getContext(), postAdoptAnimal.getImageUri(), holder.binding.imageView);
        holder.binding.descriptionText.setText(postAdoptAnimal.getDescription());
        holder.binding.datePostedText.setText(convertTimestampToString(postAdoptAnimal.getDatePosted()));

        holder.binding.getRoot().setOnClickListener(view -> {
            if (animalPostClickListener != null && postAdoptAnimal.getPostID() != null) {
                animalPostClickListener.onMovieClicked(postAdoptAnimal.getPostID(), postAdoptAnimal.getUserID(),
                        postAdoptAnimal.getName(),postAdoptAnimal.getType(),
                        postAdoptAnimal.getGenus(),postAdoptAnimal.getAge(),
                        postAdoptAnimal.getGender(),postAdoptAnimal.getDescription(),postAdoptAnimal.getImageUri(),
                        postAdoptAnimal.getAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postAdoptAnimalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemViewHomeBinding binding;

        public ViewHolder(ItemViewHomeBinding binding) {
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

 */
