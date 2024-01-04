package com.bulentoral.pawpal.ui.lost;

import com.google.firebase.Timestamp;

public interface AnimalLostPostClickListener {
    void onMovieClicked(String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri,  String address, String dateLost, double award);
}
