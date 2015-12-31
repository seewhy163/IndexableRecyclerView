package me.seewhy.letterbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
        mLetterBar.setOnLetterSelectListener(new LetterBar.OnLetterSelectListener() {
            @Override
            public void onLetterSelect(int position, String letter, boolean confirm) {
                if (confirm) {
                    mTextView.setVisibility(View.GONE);
                } else {
                    mTextView.setVisibility(View.VISIBLE);
                    mTextView.setText(letter);
                }
            }
        });
        List<BannerModel> models = new ArrayList<>();
        models.add(new BannerModel("A", "2"));
        models.add(new BannerModel("B", "2"));
        models.add(new BannerModel("C", "2"));
        models.add(new BannerModel("D", "2"));

        models.add(new BannerModel("A", "2"));
        models.add(new BannerModel("B", "2"));
        models.add(new BannerModel("D", "2"));
        models.add(new BannerModel("E", "2"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new BannerAdapter(this, models));
    }

}