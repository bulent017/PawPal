package com.bulentoral.pawpal.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AnimalService {

    @GET("/v1/animals")
    Call<List<AnimalResponse>> getAnimalFacts(@Header("X-Api-Key") String apiKey, @Query("name") String name);

}
