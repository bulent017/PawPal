package com.bulentoral.pawpal.ui.profile;

import androidx.lifecycle.ViewModel;

import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.OnAdoptAnimalsFetchedListener;
import com.bulentoral.pawpal.ui.lost.OnLostAnimalsFetchedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends ViewModel {
    private FirebaseFirestore firebaseFirestore;

    public ProfileViewModel(){

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void fetchAdoptAnimalPosts(OnAdoptAnimalsFetchedListener listener) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();
            System.out.println(currentUserId);
            firebaseFirestore.collection("adoptationPosts")
                    .whereEqualTo("userID", currentUserId)
                    .orderBy("datePosted", Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<PostAdoptAnimal> posts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                posts.add(document.toObject(PostAdoptAnimal.class));
                            }
                            listener.onFetched(posts);
                        } else {
                            listener.onError(task.getException());
                        }
                    })
                    .addOnFailureListener(listener::onError);
        } else {
            // Kullanıcı giriş yapmamışsa veya hata varsa
            listener.onError(new Exception("No authenticated user found."));
        }
    }
    public void fetchAnimalLostAnimalPosts(OnLostAnimalsFetchedListener listener) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();
            System.out.println(currentUserId);
            firebaseFirestore.collection("lostAnimalPosts")
                    .whereEqualTo("userID", currentUserId)
                    .orderBy("datePosted", Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<PostLostAnimal> posts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                posts.add(document.toObject(PostLostAnimal.class));
                            }
                            listener.onFetched(posts);
                        } else {
                            listener.onError(task.getException());
                        }
                    })
                    .addOnFailureListener(listener::onError);
        } else {
            // Kullanıcı giriş yapmamışsa veya hata varsa
            listener.onError(new Exception("No authenticated user found."));
        }
    }

}
