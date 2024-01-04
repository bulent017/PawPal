package com.bulentoral.pawpal.ui.profile;

import static com.bulentoral.pawpal.util.GlideExtensions.loadSquareImageFromURL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bulentoral.pawpal.R;
import com.bulentoral.pawpal.databinding.ItemViewHomeBinding;
import com.bulentoral.pawpal.databinding.ListItemLostAnimalBinding;
import com.bulentoral.pawpal.databinding.ListItemProfileAdoptAnimalBinding;
import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.AnimalPostClickListener;
import com.bulentoral.pawpal.ui.lost.AnimalLostPostClickListener;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class PostAdoptAdapter extends RecyclerView.Adapter<PostAdoptAdapter.ViewHolder>  {

    private List<PostAdoptAnimal> postAdoptAnimalList;
    private Context context;

    public  PostAdoptAdapter(List<PostAdoptAnimal> movieList,Context context
    ) {
        this.postAdoptAnimalList = movieList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemProfileAdoptAnimalBinding binding = ListItemProfileAdoptAnimalBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostAdoptAnimal postAdoptAnimal = postAdoptAnimalList.get(position);
        loadSquareImageFromURL(holder.binding.imageView.getContext(), postAdoptAnimal.getImageUri(), holder.binding.imageView);
        holder.binding.descriptionText.setText(postAdoptAnimal.getDescription());
        holder.binding.datePostedText.setText(convertTimestampToString(postAdoptAnimal.getDatePosted()));

        final int currentPosition = position;

        holder.binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v, currentPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postAdoptAnimalList.size();
    }
    private void showPopupMenu(View view, final int position) {
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        popup.inflate(R.menu.item_delete_menu); // Menüdeki delete öğesi için your_menu.xml
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // if-else kullanımı
                if (item.getItemId() == R.id.action_delete) {
                    // Delete seçeneğine basıldığında yapılacak işlemler

                    String postID =postAdoptAnimalList.get(position).getPostID();
                    System.out.println(postID);
                    FirebaseUtil.getAdoptationPostsReference(postID).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            postAdoptAnimalList.remove(position);
                            notifyItemRemoved(position);
                            notifyDataSetChanged(); // Tüm listenin güncellenmesi
                            if(task.isSuccessful()) {
                                Toast.makeText(context,"Post Deleted", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                    return true;
                }
                return false;
            }
        });
        popup.show();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ListItemProfileAdoptAnimalBinding binding;

        public ViewHolder(ListItemProfileAdoptAnimalBinding binding) {
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
