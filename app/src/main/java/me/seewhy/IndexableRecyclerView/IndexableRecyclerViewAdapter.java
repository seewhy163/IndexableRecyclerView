package me.seewhy.IndexableRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class IndexableRecyclerViewAdapter extends RecyclerView.Adapter implements SectionedRecyclerAdapter.SectionedRecyclerDelegate {
    public static final String TAG = "IndexableRecyclerViewAdapter";
    public static final int TYPE_BANNER = 0;
    private final LayoutInflater mLayoutInflater;

    private List<ItemModel> mItemModels;
    private int mLineNumber = 0;
    LinkedHashMap<String, List<ItemModel>> mSectionedHashMap;

    public IndexableRecyclerViewAdapter(Context context, List<ItemModel> models) {
        mItemModels = models;
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        mSectionedHashMap = new LinkedHashMap<>();
        Collections.sort(mItemModels);
        mSections.clear();
        for (int i = 0; i < mItemModels.size(); i++) {
            String ch = HanziToPinyin.getFirstPinYinChar(mItemModels.get(i).name);
            if (ch == null || ch.isEmpty() || !Character.isUpperCase(ch.codePointAt(0)))
                ch = "#";
            List<ItemModel> itemModels = mSectionedHashMap.get(ch);
            if (itemModels == null) {
                itemModels = new ArrayList<>();
            }
            itemModels.add(mItemModels.get(i));
            mSectionedHashMap.put(ch, itemModels);
        }
        calculateSectionPosition();
    }

    private void calculateSectionPosition() {
        Set<String> keySet = mSectionedHashMap.keySet();
        String strings[] = new String[keySet.size()];
        keySet.toArray(strings);
        Arrays.sort(strings);
        int pos = 0;
        for (String title : strings) {
            SectionedRecyclerAdapter.Section section = new SectionedRecyclerAdapter.Section(pos, title);
            mSections.add(section);
            pos += mSectionedHashMap.get(title).size();
        }

        mLineNumber = pos;
    }

    @Override
    public List<SectionedRecyclerAdapter.Section> getSections() {
        return mSections;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(mLayoutInflater.inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BannerViewHolder) holder).mImageView.setImageResource(mItemModels.get(position).resourceId);
    }

    @Override
    public int getItemCount() {
        return mLineNumber;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_BANNER;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}


