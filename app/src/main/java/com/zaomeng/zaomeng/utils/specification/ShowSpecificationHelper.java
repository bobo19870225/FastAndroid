package com.zaomeng.zaomeng.utils.specification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Sampson on 2019-04-30.
 * FastAndroid
 */
public class ShowSpecificationHelper {
    private int oldPosition = -1;
    private InterfaceShowSpecification interfaceShowSpecification;
    private String specificationName;
    private String objectFeatureItemID;
    private BottomSheetDialog bottomSheetDialog;
    private TextView price;

    public ShowSpecificationHelper(@NonNull Context context, InterfaceShowSpecification interfaceShowSpecification) {
        this.interfaceShowSpecification = interfaceShowSpecification;
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setCancelable(false);
    }

    public void showSpecificationDialog(LayoutInflater layoutInflater, List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList,
                                        String goodsID, double realPrice, int stock, boolean isLoading) {

        //默认Cancelable和CanceledOnTouchOutside均为true
        //bsDialog.setCancelable(true);
        //bsDialog.setCanceledOnTouchOutside(true);
        //为Dialog设置布局
//        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.dialog_goods_specification, null, false);
        LinearLayout root = view.findViewById(R.id.root);
        ProgressBar loading = view.findViewById(R.id.loading);
        //    private AlertDialog dialog;
        RecyclerView recyclerView = view.findViewById(R.id.list);
        Button ok = view.findViewById(R.id.ok);
        ImageView cancel = view.findViewById(R.id.cancel);
        TextView textView = view.findViewById(R.id.textView);
        cancel.setOnClickListener(v -> {
            if (bottomSheetDialog.isShowing())
                bottomSheetDialog.dismiss();
        });
        TextView number = view.findViewById(R.id.number);
        ImageView add = view.findViewById(R.id.add);
        add.setOnClickListener(v -> {
            int sl = Integer.valueOf(number.getText().toString());
            sl += 1;
            if (sl > stock) {
                Toast.makeText(add.getContext(), "库存不足", Toast.LENGTH_LONG).show();
                return;
            }
            number.setText(String.valueOf(sl));
        });
        ImageView reduce = view.findViewById(R.id.reduce);
        reduce.setOnClickListener(v -> {
            int sl = Integer.valueOf(number.getText().toString());
            if (sl == 1)
                return;
            sl -= 1;
            number.setText(String.valueOf(sl));
        });
        price = view.findViewById(R.id.price);
        TextView tvStock = view.findViewById(R.id.stock);
        setPrice(realPrice);
        tvStock.setText(String.valueOf(stock));
        bottomSheetDialog.setContentView(view);
        if (isLoading) {
            root.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            root.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            if (itemList != null) {
                textView.setVisibility(View.VISIBLE);
                FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(view.getContext());
                layoutManager.setFlexWrap(FlexWrap.WRAP);
                layoutManager.setFlexDirection(FlexDirection.ROW);
                layoutManager.setAlignItems(AlignItems.STRETCH);
                layoutManager.setJustifyContent(JustifyContent.FLEX_START);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new CommonAdapter<SpecificationsBean.BodyBean.DataBean.ItemListBean>(view.getContext(), R.layout.flexbox_item_text, itemList) {
                    @Override
                    protected void convert(ViewHolder holder, SpecificationsBean.BodyBean.DataBean.ItemListBean itemListBean, int position) {

                        TextView te = holder.getView(R.id.imageview);
                        te.setText(itemListBean.getObjectFeatureItemName());
                        if (oldPosition == position) {
                            te.setBackground(view.getContext().getResources().getDrawable(R.drawable.button_them_color_select));
                        } else {
                            te.setBackground(view.getContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
                        }
                        te.setOnClickListener(v -> {
                                    objectFeatureItemID = itemListBean.getObjectFeatureItemID();
                                    specificationName = itemListBean.getObjectFeatureItemName();
                                    oldPosition = position;
//                        price.setText(itemListBean.);
                                    interfaceShowSpecification.getPrice(goodsID, objectFeatureItemID);
                                    notifyDataSetChanged();
                                }
                        );
                        ViewGroup.LayoutParams lp = te.getLayoutParams();
                        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                            FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
                            flexBoxLp.width = itemListBean.getObjectFeatureItemName().getBytes().length * 32;
//                        flexBoxLp.setFlexGrow(1.0f);
//                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
                        }

                    }

                });
            } else if (specificationName != null) {
                textView.setText(String.format("规格：%s", specificationName));
            } else {
                textView.setVisibility(View.GONE);
            }

            ok.setOnClickListener(v -> {
                int qty = Integer.parseInt(number.getText().toString());
                if (itemList != null && specificationName == null) {
                    interfaceShowSpecification.toast("请选择规格");
                    return;
                }
                if (qty == 0) {
                    interfaceShowSpecification.toast("请选择数量");
                    return;
                }

                interfaceShowSpecification.callBack(goodsID, qty, objectFeatureItemID);

                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
            });
        }
        bottomSheetDialog.setOnDismissListener(dialog -> oldPosition = -1);
        if (!bottomSheetDialog.isShowing()) {
            bottomSheetDialog.show();
        }
    }

    public void setPrice(double price) {
        if (this.price != null) {
            this.price.setText(FormatUtils.numberFormatMoney(price));
        }
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
}
