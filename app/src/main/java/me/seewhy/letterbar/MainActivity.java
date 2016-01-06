package me.seewhy.letterbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private LetterBar mLetterBar;
    private TextView mTextView;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
    }

    private void initViews() {
        mLetterBar = (LetterBar) findViewById(R.id.letterBar);
        mTextView = (TextView) findViewById(R.id.text);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
    public static final String URL = "http://10.45.17.146/download/";
    private void initData() {

        List<BannerModel> models = new ArrayList<>();
        models.add(new BannerModel("国睿科技", URL + "grkj" + ".png"));
        models.add(new BannerModel("海格雷", URL + "hgl" + ".png"));
        models.add(new BannerModel("恒华", URL + "hh" + ".png"));
        models.add(new BannerModel("海的", URL + "hd" + ".png"));
        models.add(new BannerModel("恒瑞", URL + "hr" + ".png"));
        models.add(new BannerModel("佳美", URL + "jm" + ".png"));
        models.add(new BannerModel("皖中", URL + "wz" + ".png"));
        models.add(new BannerModel("东昕", URL + "dx" + ".png"));
        models.add(new BannerModel("真武", URL + "zw" + ".png"));
        models.add(new BannerModel("日生", URL + "rs" + ".png"));
        models.add(new BannerModel("化陶", URL + "ht" + ".png"));
        models.add(new BannerModel("玉泉", URL + "yq" + ".png"));
        models.add(new BannerModel("老田", URL + "lt" + ".png"));
        models.add(new BannerModel("女孩", URL + "nh" + ".png"));

//        models.add(new BannerModel("A", "1"));
//        models.add(new BannerModel("A", "2"));
//        models.add(new BannerModel("A", "3"));
//        models.add(new BannerModel("A", "4"));
//        models.add(new BannerModel("A", "5"));
//        models.add(new BannerModel("B", "6"));
//        models.add(new BannerModel("B", "7"));
//        models.add(new BannerModel("B", "8"));
//        models.add(new BannerModel("B", "9"));
//        models.add(new BannerModel("C", "10"));
//        models.add(new BannerModel("D", "11"));
//        models.add(new BannerModel("D", "12"));
//        models.add(new BannerModel("E", "13"));
//        models.add(new BannerModel("C", "14"));
//        models.add(new BannerModel("D", "15"));
//        models.add(new BannerModel("D", "16"));
//        models.add(new BannerModel("E", "17"));
//        models.add(new BannerModel("D", "19"));
//        models.add(new BannerModel("D", "20"));
//        models.add(new BannerModel("X", "21"));
//        models.add(new BannerModel("Y", "22"));
//        models.add(new BannerModel("Z", "23"));
//        models.add(new BannerModel("G", "24"));

//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        final BannerAdapter bannerAdapter = new BannerAdapter(this, models);
        final SectionedRecyclerAdapter recyclerAdapter = new SectionedRecyclerAdapter(this, R.layout.title_item, R.id.tvName, bannerAdapter);
        mRecyclerView.setAdapter(recyclerAdapter);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return recyclerAdapter.isSectionHeaderPosition(position) ? 3 : 1;
            }
        });
        mLetterBar.setOnLetterSelectListener(new LetterBar.OnLetterSelectListener() {
            @Override
            public void onLetterSelect(int position, String letter, boolean confirmed) {
                if (confirmed) {
                    mTextView.setVisibility(View.GONE);
                } else {
                    mTextView.setVisibility(View.VISIBLE);
                    mTextView.setText(letter + position);
                }
                Integer sectionPosition = recyclerAdapter.getSectionPosition(position);
                if (sectionPosition != null)
                    gridLayoutManager.scrollToPositionWithOffset(sectionPosition, 0);
            }
        });
    }

}