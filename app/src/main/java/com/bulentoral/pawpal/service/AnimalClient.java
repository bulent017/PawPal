package com.bulentoral.pawpal.service;

import android.util.Log;

import com.bulentoral.pawpal.InfoFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalClient {

    private static final String API_URL = "https://api.api-ninjas.com/";
    private static final String API_KEY = "nCi0FT8eMAL8qdVYoE6TjvaTAF4PDLKMqrg6N7Ej";


    public static void someMethod(String animal, InfoFragment.AnimalResponseCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimalService apiService = retrofit.create(AnimalService.class);

        Call<List<AnimalResponse>> call = apiService.getAnimalFacts(API_KEY, animal);

        call.enqueue(new Callback<List<AnimalResponse>>() {
            @Override
            public void onResponse(Call<List<AnimalResponse>> call, Response<List<AnimalResponse>> response) {
                if (response.isSuccessful()) {
                    List<AnimalResponse> animals = response.body();
                    if (animals != null && !animals.isEmpty()) {
                        callback.onSuccess(animals.get(0)); // İlk hayvanı döndür
                    } else {
                        callback.onFailure("Response body is empty or null!");
                    }
                } else {
                    callback.onFailure("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<AnimalResponse>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

}
