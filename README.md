# IndexableRecyclerView

This is an Android Indexable Recyclerview, which can set multiple columns.

# Screenshots

![image](https://github.com/seewhy93/IndexableRecyclerView/blob/master/screenshot.png?raw=true)

# Usage

1. Create an IndexableRecyclerView in xml.

 ```
<me.seewhy.IndexableRecyclerView.IndexableRecyclerView
        android:id="@+id/indexable_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:recyclerColumns="3"/>
```

2. Create your own adapter and call `setAdapter` method.

 ```
List<ItemModel> models = new ArrayList<>();
.
.
.
models.add(new ItemModel("-1", R.mipmap.nh));

IndexableRecyclerViewAdapter indexableRecyclerViewAdapter = new IndexableRecyclerViewAdapter(this, models);
mIndexableRecyclerView.setAdapter(indexableRecyclerViewAdapter);
```


