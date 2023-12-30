package com.bulentoral.pawpal.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Completable;

public class FirebaseSource {

    private static FirebaseSource instance;
    private final FirebaseAuth firebaseAuth;

    // Özel kurucu
    private FirebaseSource() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    // Singleton örneğini döndüren metot
    public static synchronized FirebaseSource getInstance() {
        if (instance == null) {
            instance = new FirebaseSource();
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

    public void logout() {
        firebaseAuth.signOut();
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
