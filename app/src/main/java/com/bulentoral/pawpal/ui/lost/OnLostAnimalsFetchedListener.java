package com.bulentoral.pawpal.ui.lost;

import com.bulentoral.pawpal.model.PostAdoptAnimal;
import com.bulentoral.pawpal.model.PostLostAnimal;

import java.util.List;

public interface OnLostAnimalsFetchedListener {
    void onFetched(List<PostLostAnimal> adoptAnimalPosts);
    void onError(Exception e);
}

