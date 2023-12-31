package com.bulentoral.pawpal.ui.auth;

import androidx.lifecycle.ViewModel;

import com.bulentoral.pawpal.util.FirebaseUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel{

    private final FirebaseUtil firebaseUtil;
    private final CompositeDisposable disposables;

    public AuthViewModel() {
        this.firebaseUtil = FirebaseUtil.getInstance();
        this.disposables = new CompositeDisposable();
    }

    // Kullanıcı girişi için işlem
    public void loginUser(String email, String password, OnAuthListener listener) {
        disposables.add(firebaseUtil.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onSuccess, listener::onError));
    }

    // Kullanıcı kaydı için işlem
    public void registerUser(String email, String password, OnAuthListener listener) {
        disposables.add(firebaseUtil.register(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onSuccess, listener::onError));
    }

    // Kullanıcı çıkışı için işlem
    public void logoutUser() {
        firebaseUtil.logout();
    }

    // Kullanıcı oturumu açık mı kontrolü
    public boolean isUserLoggedIn() {
        return firebaseUtil.getCurrentUser() != null;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    // Auth işlemleri için listener arayüzü
    public interface OnAuthListener {
        void onSuccess();
        void onError(Throwable throwable);
    }

}
