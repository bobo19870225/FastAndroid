package com.jinkan.www.fastandroid.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.by_page.ByPageKeyRepository;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ApiService apiService;
    @Inject
    ByPageKeyRepository byPageKeyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTest = findViewById(R.id.test);
        RecyclerView recyclerView = findViewById(R.id.list);
        final MovieAdapter movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        Listing<Subjects> movieListing = byPageKeyRepository.post("", 10);
        movieListing.getPagedList().observe(this, new Observer<PagedList<Subjects>>() {
            @Override
            public void onChanged(PagedList<Subjects> subjects) {
                movieAdapter.submitList(subjects);
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.getTopMovie(0, 10);
            }
        });

    }
}
