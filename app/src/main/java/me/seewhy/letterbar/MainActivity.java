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

    private void initData() {

        List<ImageModel> models = new ArrayList<>();
        models.add(new ImageModel("国睿科技", R.mipmap.grkj));
        models.add(new ImageModel("海格雷", R.mipmap.hgl));
        models.add(new ImageModel("恒华", R.mipmap.hh));
        models.add(new ImageModel("海的", R.mipmap.hd));
        models.add(new ImageModel("恒瑞", R.mipmap.hr));
        models.add(new ImageModel("佳美", R.mipmap.jm));
        models.add(new ImageModel("皖中", R.mipmap.wz));
        models.add(new ImageModel("东昕", R.mipmap.dx));
        models.add(new ImageModel("真武", R.mipmap.zw));
        models.add(new ImageModel("日生", R.mipmap.rs));
        models.add(new ImageModel("化陶", R.mipmap.ht));
        models.add(new ImageModel("玉泉", R.mipmap.yq));
        models.add(new ImageModel("老田", R.mipmap.lt));
        models.add(new ImageModel("女孩", R.mipmap.nh));

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        final ImageAdapter imageAdapter = new ImageAdapter(this, models);
        final SectionedRecyclerAdapter recyclerAdapter = new SectionedRecyclerAdapter(this, R.layout.title_item, R.id.tvName, imageAdapter);
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
                    mTextView.setText(letter);
                }
                Integer sectionPosition = recyclerAdapter.getSectionPosition(position);
                if (sectionPosition != null)
                    gridLayoutManager.scrollToPositionWithOffset(sectionPosition, 0);
            }
        });
    }

}