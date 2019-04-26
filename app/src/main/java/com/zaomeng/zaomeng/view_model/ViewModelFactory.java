/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.by_page.GoodsPageKeyRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */
@Singleton
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;

    @Inject
    public ViewModelFactory(Application application) {
        this.application = application;
    }

    @Inject
    GoodsPageKeyRepository goodsPageKeyRepository;
    @Inject
    ApiService apiService;
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(GoodsFragmentVM.class)) {
            //noinspection unchecked
            return (T) new GoodsFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            //noinspection unchecked
            return (T) new RegisterViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(CertificationVM.class)) {
            //noinspection unchecked
            return (T) new CertificationVM(application, apiService);
        } else if (modelClass.isAssignableFrom(SortFragmentVM.class)) {
            //noinspection unchecked
            return (T) new SortFragmentVM(application, apiService, goodsPageKeyRepository);
        } else if (modelClass.isAssignableFrom(GoodsDetailsVM.class)) {
            //noinspection unchecked
            return (T) new GoodsDetailsVM(application, apiService);
        } else if (modelClass.isAssignableFrom(MainFragmentVM.class)) {
            //noinspection unchecked
            return (T) new MainFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(CommonlyUsedFragmentVM.class)) {
            //noinspection unchecked
            return (T) new CommonlyUsedFragmentVM(application, apiService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }


}
