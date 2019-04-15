package com.jinkan.www.fastandroid.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.Range;
import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.data.DEBUG;
import com.jinkan.www.fastandroid.data.RatioTextView;
import com.jinkan.www.fastandroid.data.SimpleImgView;
import com.jinkan.www.fastandroid.data.SingleImageView;
import com.jinkan.www.fastandroid.data.TestView;
import com.jinkan.www.fastandroid.data.TestViewHolder;
import com.jinkan.www.fastandroid.data.TestViewHolderCell;
import com.jinkan.www.fastandroid.data.VVTEST;
import com.jinkan.www.fastandroid.support.SampleClickSupport;
import com.jinkan.www.fastandroid.view.base.BaseDaggerFragment;
import com.jinkan.www.fastandroid.view.custom_view.LocationView;
import com.libra.Utils;
import com.squareup.picasso.Picasso;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.core.adapter.GroupBasicAdapter;
import com.tmall.wireless.tangram.dataparser.concrete.Card;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.viewcreator.ViewHolderCreator;
import com.tmall.wireless.tangram.support.async.CardLoadSupport;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static android.os.Looper.getMainLooper;

/**
 * Created by Sampson on 2019/4/12.
 * FastAndroid
 */
public class HomeFragment extends BaseDaggerFragment {
    @Inject
    public HomeFragment() {
    }

    private Handler mMainHandler;
    TangramEngine engine;
    TangramBuilder.InnerBuilder builder;
    RecyclerView recyclerView;

//    @Override
//    protected String setToolBarTitle() {
//        return null;
//    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initUI() {
        recyclerView = rootView.findViewById(R.id.main_view);
        Context context = getContext();
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            //Step 1: init tangram
            TangramBuilder.init(applicationContext, new IInnerImageSetter() {
                @Override
                public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view,
                                                                     @Nullable String url) {
                    Picasso.with(applicationContext).load(url).into(view);
                }
            }, ImageView.class);
            mMainHandler = new Handler(getMainLooper());
            //Step 2: register build=in cells and cards
            builder = TangramBuilder.newInnerBuilder(applicationContext);
            //Step 3: register business cells and cards
            builder.registerCell("0", LocationView.class);
            builder.registerCell("1", TestView.class);
            builder.registerCell("10", SimpleImgView.class);
            builder.registerCell("2", SimpleImgView.class);
            builder.registerCell("4", RatioTextView.class);
            builder.registerCell("110",
                    TestViewHolderCell.class,
                    new ViewHolderCreator<>(R.layout.item_holder, TestViewHolder.class, TextView.class));
            builder.registerCell("199", SingleImageView.class);
            builder.registerVirtualView("vvtest");

            //Step 4: new engine
            engine = builder.build();
            engine.setVirtualViewTemplate(VVTEST.BIN);
            engine.setVirtualViewTemplate(DEBUG.BIN);
            Utils.setUedScreenWidth(720);
            //Step 5: add card load support if you have card that loading cells async
            engine.addCardLoadSupport(new CardLoadSupport(
                    (card, callback) -> {
                        Log.w("Load Card", card.load);

                        mMainHandler.postDelayed(() -> {
                            // do loading
                            JSONArray cells = new JSONArray();

                            for (int i = 0; i < 10; i++) {
                                try {
                                    JSONObject obj = new JSONObject();
                                    obj.put("type", 1);
                                    obj.put("msg", "async loaded");
                                    JSONObject style = new JSONObject();
                                    style.put("bgColor", "#FF1111");
                                    obj.put("style", style.toString());
                                    cells.put(obj);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            // callback.fail(false);
                            callback.finish(engine.parseComponent(cells));
                        }, 200);
                    },

                    (page, card, callback) -> {
                        mMainHandler.postDelayed(() -> {
                            Log.w("Load page", card.load + " page " + page);
                            JSONArray cells = new JSONArray();
                            for (int i = 0; i < 9; i++) {
                                try {
                                    JSONObject obj = new JSONObject();
                                    obj.put("type", 1);
                                    obj.put("msg", "async page loaded, params: " + card.getParams().toString());
                                    cells.put(obj);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            List<BaseCell> cs = engine.parseComponent(cells);

                            if (card.page == 1) {
                                GroupBasicAdapter<Card, ?> adapter = engine.getGroupBasicAdapter();

                                card.setCells(cs);
                                adapter.refreshWithoutNotify();
                                Range<Integer> range = adapter.getCardRange(card);

                                adapter.notifyItemRemoved(range.getLower());
                                adapter.notifyItemRangeInserted(range.getLower(), cs.size());

                            } else
                                card.addCells(cs);

                            //mock load 6 pages
                            callback.finish(card.page != 6);
                            card.notifyDataChange();
                        }, 400);
                    }));
            engine.addSimpleClickSupport(new SampleClickSupport());

            //Step 6: enable auto load more if your page's data is lazy loaded
            engine.enableAutoLoadMore(true);
//            engine.register(InternalErrorSupport.class, new SampleErrorSupport());

            //Step 7: bind recyclerView to engine
            engine.bindView(recyclerView);

            //Step 8: listener recyclerView onScroll event to trigger auto load more
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    engine.onScrolled();
                }
            });

            //Step 9: set an offset to fix card
            engine.getLayoutManager().setFixOffset(0, 40, 0, 0);
            //Step 10: get tangram data and pass it to engine
            String json = new String(getAssertsFile(applicationContext, "data.json"));
            JSONArray data;
            try {
                data = new JSONArray(json);
                engine.setData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] getAssertsFile(Context context, String fileName) {
        InputStream inputStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(fileName);
            if (inputStream == null) {
                return null;
            }

            BufferedInputStream bis = null;
            int length;
            try {
                bis = new BufferedInputStream(inputStream);
                length = bis.available();
                byte[] data = new byte[length];
                bis.read(data);

                return data;
            } catch (IOException e) {

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {

                    }
                }
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void initData(Object transferData) {

    }
}
