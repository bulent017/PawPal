package com.bulentoral.pawpal.util;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bulentoral.pawpal.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.Completable;

public class FirebaseUtil {

    private static FirebaseUtil instance;
    private final FirebaseAuth firebaseAuth;

    // Özel kurucu
    private FirebaseUtil() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    // Singleton örneğini döndüren metot
    public static synchronized FirebaseUtil getInstance() {
        if (instance == null) {
            instance = new FirebaseUtil();
        }
        return instance;
    }

    public Completable login(String email, String password) {
        return Completable.create(emitter -> {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (!emitter.isDisposed()) {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }
            });
        });
    }

    public Completable register(String email, String password) {
        return Completable.create(emitter -> {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (!emitter.isDisposed()) {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }
            });
        });
    }

    public boolean isUserLoggedIn() {
        return getCurrentUser() != null;
    }

    public void logout() {
        firebaseAuth.signOut();
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }


    public static String currentUserId() {
        return FirebaseAuth.getInstance().getUid();
    }

    public static DocumentReference currentUserDetails() {
        return FirebaseFirestore.getInstance().collection("users").document(currentUserId());
    }

    public static CollectionReference allUserCollectionReference() {
        return FirebaseFirestore.getInstance().collection("users");
    }

    public static DocumentReference getChatroomReference(String chatroomId) {
        return FirebaseFirestore.getInstance().collection("chatrooms").document(chatroomId);
    }

    public static CollectionReference getChatroomMessageReference(String chatroomId) {
        return getChatroomReference(chatroomId).collection("chats");
    }

    public static String getChatroomId(String userId1, String userId2) {
        if (userId1.hashCode() < userId2.hashCode()) {
            return userId1 + "_" + userId2;
        } else {
            return userId2 + "_" + userId1;
        }
    }

    public static CollectionReference allChatroomCollectionReference() {
        return FirebaseFirestore.getInstance().collection("chatrooms");
    }

    public static DocumentReference getOtherUserFromChatroom(List<String> userIds) {
        if (userIds.get(0).equals(FirebaseUtil.currentUserId())) {
            return allUserCollectionReference().document(userIds.get(1));
        } else {
            return allUserCollectionReference().document(userIds.get(0));
        }
    }

    public static String timestampToString(Timestamp timestamp) {
        return new SimpleDateFormat("HH:MM").format(timestamp.toDate());
    }

    public static StorageReference getCurrentProfilePicStorageRef() {
        return FirebaseStorage.getInstance().getReference().child("profile_pic")
                .child(FirebaseUtil.currentUserId());
    }

    public static StorageReference getOtherProfilePicStorageRef(String otherUserId) {
        return FirebaseStorage.getInstance().getReference().child("profile_pic")
                .child(otherUserId);
    }

}