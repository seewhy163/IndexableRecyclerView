package me.seewhy.IndexableRecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private IndexableRecyclerView mIndexableRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
    }

    private void initViews() {
        mIndexableRecyclerView = (IndexableRecyclerView) findViewById(R.id.indexable_recyclerview);
    }

    private void initData() {

        List<ItemModel> models = new ArrayList<>();
        models.add(new ItemModel("国睿科技", R.mipmap.grkj));
        models.add(new ItemModel("海格雷", R.mipmap.hgl));
        models.add(new ItemModel("恒华", R.mipmap.hh));
        models.add(new ItemModel("海的", R.mipmap.hd));
        models.add(new ItemModel("恒瑞", R.mipmap.hr));
        models.add(new ItemModel("佳美", R.mipmap.jm));
        models.add(new ItemModel("皖中", R.mipmap.wz));
        models.add(new ItemModel("东昕", R.mipmap.dx));
        models.add(new ItemModel("真武", R.mipmap.zw));
        models.add(new ItemModel("日生", R.mipmap.rs));
        models.add(new ItemModel("化陶", R.mipmap.ht));
        models.add(new ItemModel("玉泉", R.mipmap.yq));
        models.add(new ItemModel("老田", R.mipmap.lt));
        models.add(new ItemModel("女孩", R.mipmap.nh));
        models.add(new ItemModel("360", R.mipmap.nh));
        models.add(new ItemModel("-1", R.mipmap.nh));

        IndexableRecyclerViewAdapter indexableRecyclerViewAdapter = new IndexableRecyclerViewAdapter(this, models);
        mIndexableRecyclerView.setAdapter(indexableRecyclerViewAdapter);

    }

}