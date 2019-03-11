package com.jinkan.www.fastandroid.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.by_page.ByPageKeyRepository;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ApiService apiService;
    private String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTest = findViewById(R.id.test);
        final MovieAdapter movieAdapter = new MovieAdapter();
        ByPageKeyRepository byPageKeyRepository = new ByPageKeyRepository();
        Listing<Movie> movieListing = byPageKeyRepository.post("", 10);
        movieListing.getPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movieAdapter.submitList(movies);
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
