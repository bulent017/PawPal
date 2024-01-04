package com.bulentoral.pawpal.ui.adopt.adapter;


import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.view.LayoutInflater;
        import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.bulentoral.pawpal.databinding.ItemViewHomeBinding;
import com.bulentoral.pawpal.databinding.ListItemLostAnimalBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.ui.adopt.AnimalPostClickListener;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostAdoptationAdapter extends RecyclerView.Adapter<PostAdoptationAdapter.ViewHolder> implements Filterable {

    private List<PostAdoptAnimal> postAdoptAnimalList;
    private AnimalPostClickListener animalPostClickListener;

    private List<PostAdoptAnimal>  filterPostList;

    public  PostAdoptationAdapter(List<PostAdoptAnimal> movieList,
                                 AnimalPostClickListener animalPostClickListener
    ) {
        this.postAdoptAnimalList = movieList;
        this.animalPostClickListener = animalPostClickListener;
        this.filterPostList = new ArrayList<>(postAdoptAnimalList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewHomeBinding binding = ItemViewHomeBinding.inflate(inflater, parent, false);
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

    @Override
    public Filter getFilter() {
        return postFilter;
    }
    private Filter postFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PostAdoptAnimal> filteredList = new ArrayList<>();
            if(charSequence==null || charSequence.length() == 0){
                filteredList.addAll(filterPostList);
            }
            else{
                String filterPattern  = charSequence.toString().toLowerCase().trim();
                for (PostAdoptAnimal item: filterPostList
                     ) {
                    if (item.getAddress().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            postAdoptAnimalList.clear();
            postAdoptAnimalList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
    //String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri, Timestamp datePosted, String address, boolean adoptionStatus


