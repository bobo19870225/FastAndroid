package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.SingleLiveEvent;
import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.http.by_page.ByPageKeyRepository;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/3/13.
 * FastAndroid
 */
public class MainViewModel extends BaseViewModel {
    public final SingleLiveEvent<Void> singleLiveEvent = new SingleLiveEvent<>();
    public Listing<Subjects> movieListing;
    private ByPageKeyRepository byPageKeyRepository;

    public MainViewModel(@NonNull Application application, ByPageKeyRepository byPageKeyRepository) {
        super(application);
        this.byPageKeyRepository = byPageKeyRepository;
    }

    @Override
    public void init() {
        movieListing = byPageKeyRepository.post("", 10);
    }

    public void test() {
        singleLiveEvent.call();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
