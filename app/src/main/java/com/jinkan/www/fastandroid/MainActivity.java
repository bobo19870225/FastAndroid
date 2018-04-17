package com.jinkan.www.fastandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.http.ApiMethods;
import com.jinkan.www.fastandroid.model.http.ObserverOnNextListener;
import com.jinkan.www.fastandroid.model.http.ProgressObserver;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTest = findViewById(R.id.test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiMethods.getTopMovie(
                        new ProgressObserver<Movie>(MainActivity.this,
                                new ObserverOnNextListener<Movie>() {
                                    @Override
                                    public void onNext(Movie movie) {
                                        Log.d(TAG, "onNext: " + movie.getTitle());
                                        List<Subjects> list = movie.getSubjects();
                                        for (Subjects sub : list) {
                                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                                        }
                                    }

                                }), 0, 3);
            }
        });

    }
}
