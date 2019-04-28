package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.branch_goods.BranchGoodsPageKeyRepository;
import com.zaomeng.zaomeng.model.repository.http.by_page.common_used_Goods.CUGoodsPageKeyedDataSource;
import com.zaomeng.zaomeng.view.BranchGoodsFragment;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 * {@link BranchGoodsFragment}
 */
public class BranchGoodsFragmentVM extends ListViewModel<BranchGoodsBean> {
    private BranchGoodsPageKeyRepository branchGoodsPageKeyRepository;

    public BranchGoodsFragmentVM(@NonNull Application application, BranchGoodsPageKeyRepository branchGoodsPageKeyRepository) {
        super(application);
        this.branchGoodsPageKeyRepository = branchGoodsPageKeyRepository;
    }

    @Override
    public void init(Object data) {

    }

    /**
     * {@link CUGoodsPageKeyedDataSource}
     */
    @Override
    public Listing<BranchGoodsBean> getListing(Object data) {
        return branchGoodsPageKeyRepository.post(new String[]{(String) data,
                "422429993732"}, 10);

    }
}
