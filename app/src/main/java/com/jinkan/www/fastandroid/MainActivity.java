package com.jinkan.www.fastandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.http.ApiService;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ApiService apiService;
    private String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvContent = findViewById(R.id.content);
        Button btnTest = findViewById(R.id.test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.getTopMovie(0, 3).enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
//                ApiMethods.getTopMovie(
//                        new ProgressObserver<Movie>(MainActivity.this,
//                                new ObserverOnNextListener<Movie>() {
//                                    @Override
//                                    public void onNext(Movie movie) {
//                                        tvContent.setText(movie.getTitle());
//                                        Log.d(TAG, "onNext: " + movie.getTitle());
//                                        List<Subjects> list = movie.getSubjects();
//                                        for (Subjects sub : list) {
//                                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
//                                        }
//                                    }
//
//                                }), 0, 3);
            }
        });

    }
}
