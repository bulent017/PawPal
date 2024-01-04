package com.bulentoral.pawpal.ui.lost;

import android.net.Uri;
import android.util.Log;

import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;
import com.bulentoral.pawpal.ui.adopt.OnAdoptAnimalsFetchedListener;
import com.bulentoral.pawpal.util.FirebaseUtil;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LostAnimalViewModel {

    private static final String TAG = "LostAnimalViewModel";


    private FirebaseFirestore firebaseFirestore;

    public LostAnimalViewModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void postLostAnimal(PostLostAnimal post) {

        // Yeni bir doküman referansı oluşturun ve otomatik ID alın
        DocumentReference newPostRef = firebaseFirestore.collection("lostAnimalPosts").document();

        // Yeni oluşturulan doküman ID'sini alın
        String autoGeneratedPostId = newPostRef.getId();
        String userId = FirebaseUtil.currentUserId();

        post.setUserID(userId);
        post.setPostID(autoGeneratedPostId);
        // Post nesnesinde postID'yi set edin

        firebaseFirestore.collection("lostAnimalPosts")
                .document(post.getPostID())
                .set(post)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("Post eklendi");
                })
                .addOnFailureListener(e -> {
                    System.out.println("Post eklenemedi");
                });
    }
    public void uploadImageAndCreatePost(PostLostAnimal post) {
        Uri imageUri = Uri.parse(post.getImageUri()); // Post nesnesinden imageUri alınır
        if (imageUri != null) {
            // Firebase Storage referansını oluştur
            String userId = FirebaseUtil.currentUserId(); // Kullanıcı ID'sini al
            String timestamp = String.valueOf(System.currentTimeMillis()); // Zaman damgasını al
            String fileName = "photos/" + userId + "_" + timestamp + ".jpg"; // Benzersiz dosya adını oluştur
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference photoRef = storageRef.child(fileName);

            // Resmi yükle
            UploadTask uploadTask = photoRef.putFile(imageUri);

            // Yükleme durumunu dinle
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl().addOnSuccessListener(uri -> {
                    post.setImageUri(uri.toString());
                    Log.d("ViewModel class", uri.toString());
                    postLostAnimal(post);
                });
            }).addOnFailureListener(e -> {
                Log.e(TAG, "Image upload failed", e);
            });
        } else {
            Log.e(TAG, "Image Uri is null");
        }
    }

    public void fetchLostAnimalPosts(OnLostAnimalsFetchedListener listener) {
        firebaseFirestore.collection("adoptationPosts")
                .orderBy("datePosted", Query.Direction.DESCENDING) // Yeni postlar üstte olacak şekilde sırala
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<PostLostAnimal> posts = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            posts.add(document.toObject(PostLostAnimal.class));
                        }
                        listener.onFetched(posts); // Başarılı sonuçta, listener'a post listesini gönder
                    } else {
                        listener.onError(task.getException()); // Hata durumunda listener'a hata bilgisini gönder
                    }
                })
                .addOnFailureListener(listener::onError); // Hata durumunda listener'a bilgi ver
    }

}