package com.bulentoral.pawpal.ui.adopt;

import com.bulentoral.pawpal.model.PostAdoptAnimal;

import java.util.List;

public interface OnAdoptAnimalsFetchedListener {
    void onFetched(List<PostAdoptAnimal> adoptAnimalPosts);
    void onError(Exception e);
}

