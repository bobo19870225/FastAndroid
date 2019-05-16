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

import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.ApiService;

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
    ApiService apiService;
    @Inject
    UserDao userDao;

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {

            return (T) new LoginViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(MainGoodsFragmentVM.class)) {

            return (T) new MainGoodsFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {

            return (T) new RegisterViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(CertificationVM.class)) {

            return (T) new CertificationVM(application, apiService);
        } else if (modelClass.isAssignableFrom(SortFragmentVM.class)) {

            return (T) new SortFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(GoodsDetailsVM.class)) {

            return (T) new GoodsDetailsVM(application, apiService);
        } else if (modelClass.isAssignableFrom(MainFragmentVM.class)) {

            return (T) new MainFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(CommonlyUsedFragmentVM.class)) {

            return (T) new CommonlyUsedFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(BranchGoodsFragmentVM.class)) {

            return (T) new BranchGoodsFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(ShoppingCartFragmentVM.class)) {

            return (T) new ShoppingCartFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(MessageVM.class)) {

            return (T) new MessageVM(application, apiService);
        } else if (modelClass.isAssignableFrom(AddressManageVM.class)) {

            return (T) new AddressManageVM(application, apiService);
        } else if (modelClass.isAssignableFrom(CalendarVM.class)) {

            return (T) new CalendarVM(application, apiService);
        } else if (modelClass.isAssignableFrom(PointFragmentVM.class)) {

            return (T) new PointFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(OrderSettlementVM.class)) {

            return (T) new OrderSettlementVM(application, apiService);
        } else if (modelClass.isAssignableFrom(FeedbackVM.class)) {

            return (T) new FeedbackVM(application, apiService);
        } else if (modelClass.isAssignableFrom(OrderFragmentVM.class)) {

            return (T) new OrderFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(NewOrderFragmentVM.class)) {

            return (T) new NewOrderFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(ReceivedOrderFragmentVM.class)) {

            return (T) new ReceivedOrderFragmentVM(application, apiService);
        } else if (modelClass.isAssignableFrom(UserInfoVM.class)) {

            return (T) new UserInfoVM(application, apiService, userDao);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {

            return (T) new SearchViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(FindPasswordVM.class)) {

            return (T) new FindPasswordVM(application, apiService);
        } else if (modelClass.isAssignableFrom(SearchGoodsListVM.class)) {
            return (T) new SearchGoodsListVM(application, apiService);
        } else if (modelClass.isAssignableFrom(BonusFragmentVM.class)) {
            return (T) new BonusFragmentVM(application, apiService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }


}
